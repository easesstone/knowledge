```
Build Java
You can use Bazel to build your Java application. In this tutorial you'll learn how to:

Build your first Java target
Add dependencies to your target
Use multiple packages
Deploy your target
Setting up your workspace

Suppose that you have an existing project in a directory, say, ~/gitroot/my-project/. Create an empty file at ~/gitroot/my-project/WORKSPACE to show Bazel where your project's root is.

Creating Your Own Build File

Use the following commands to make a small Java project for this example:

$ # If you're not already there, move to your workspace directory.
$ cd ~/gitroot/my-project
$ mkdir -p src/main/java/com/example
$ cat > src/main/java/com/example/ProjectRunner.java <<'EOF'
package com.example;

public class ProjectRunner {
    public static void main(String args[]) {
        Greeting.sayHi();
    }
}
EOF
$ cat > src/main/java/com/example/Greeting.java <<'EOF'
package com.example;

public class Greeting {
    public static void sayHi() {
        System.out.println("Hi!");
    }
}
EOF
Bazel figures out what to build by looking for files named BUILD in your workspace, so we'll create a BUILD file in the ~/gitroot/my-project directory. Add the following lines to this BUILD file:

# ~/gitroot/my-project/BUILD
java_binary(
    name = "my-runner",
    srcs = glob(["**/*.java"]),
    main_class = "com.example.ProjectRunner",
)
java_binary is the type of thing this rule will build. glob(["**/*.java"]) is a handy shorthand for "recursively include every file that ends with .java" (see the build encyclopedia for more information about globbing). com.example.ProjectRunner specifies the class that contains the main method.

Now you are ready to build your Java binary:

$ cd ~/gitroot/my-project
$ bazel build //:my-runner
INFO: Found 1 target...
Target //:my-runner up-to-date:
  bazel-bin/my-runner.jar
  bazel-bin/my-runner
INFO: Elapsed time: 1.021s, Critical Path: 0.83s
$ bazel-bin/my-runner
Hi!
Congratulations, you've just built your first Bazel target!

Adding Dependencies

Creating one rule to build your entire project may be sufficient for small projects, but as projects get larger it's important to break up the build into self-contained libraries that can be assembled into a final product. This way the entire world doesn't need to be rebuilt on small changes and Bazel can parallelize more of the build steps.

To break up a project, create separate rules for each subcomponent and then make them depend on each other. For the example above, add the following rules to the BUILD file:

java_binary(
    name = "my-other-runner",
    srcs = ["src/main/java/com/example/ProjectRunner.java"],
    main_class = "com.example.ProjectRunner",
    deps = [":greeter"],
)

java_library(
    name = "greeter",
    srcs = ["src/main/java/com/example/Greeting.java"],
)
This builds the same files as before, but in a different way: now Bazel will build the greeter library first and then build my-other-runner. Try building and running //:my-other-runner:

$ bazel run //:my-other-runner
INFO: Found 1 target...
Target //:my-other-runner up-to-date:
  bazel-bin/my-other-runner.jar
  bazel-bin/my-other-runner
INFO: Elapsed time: 2.454s, Critical Path: 1.58s

INFO: Running command line: bazel-bin/my-other-runner
Hi!
Now if you edit ProjectRunner.java and rebuild my-other-runner, Greeting.java will not need to be recompiled.

Using Multiple Packages

For larger projects, you will often be dealing with several directories. You can refer to targets defined in other BUILD files using the syntax //path/to/directory:target-name. For example, suppose src/main/java/com/example/ has a cmdline/ subdirectory with the following file:

$ mkdir -p src/main/java/com/example/cmdline
$ cat > src/main/java/com/example/cmdline/Runner.java <<'EOF'
package com.example.cmdline;

import com.example.Greeting;

public class Runner {
    public static void main(String args[]) {
        Greeting.sayHi();
    }
}
EOF
Runner.java depends on com.example.Greeting, so we could add a BUILD file at src/main/java/com/example/cmdline/BUILD that contained the following rule:

# ~/gitroot/my-project/src/main/java/com/example/cmdline/BUILD
java_binary(
    name = "runner",
    srcs = ["Runner.java"],
    main_class = "com.example.cmdline.Runner",
    deps = ["//:greeter"]
)
However, by default, build rules are private. This means that they can only be referred to by rules in the same BUILD file. This prevents libraries that are implementation details from leaking into public APIs, but it also means that you must explicitly allow runner to depend on //:greeter. As is, if we build runner we'll get a permissions error:

$ bazel build //src/main/java/com/example/cmdline:runner
ERROR: /home/user/gitroot/my-project/src/main/java/com/example/cmdline/BUILD:2:1:
  Target '//:greeter' is not visible from target '//src/main/java/com/example/cmdline:runner'.
  Check the visibility declaration of the former target if you think the dependency is legitimate.
ERROR: Analysis of target '//src/main/java/com/example/cmdline:runner' failed; build aborted.
INFO: Elapsed time: 0.091s
You can make a rule visible to rules in other BUILD files by adding a visibility = level attribute. Change the greeter rule in ~/gitroot/my-project/BUILD to be visible to our new rule:

java_library(
    name = "greeter",
    srcs = ["src/main/java/com/example/Greeting.java"],
    visibility = ["//src/main/java/com/example/cmdline:__pkg__"],
)
This makes //:greeter visible to any rule in the //src/main/java/com/example/cmdline package. Now we can build and run the runner binary:

$ bazel run //src/main/java/com/example/cmdline:runner
INFO: Found 1 target...
Target //src/main/java/com/example/cmdline:runner up-to-date:
  bazel-bin/src/main/java/com/example/cmdline/runner.jar
  bazel-bin/src/main/java/com/example/cmdline/runner
INFO: Elapsed time: 1.576s, Critical Path: 0.81s

INFO: Running command line: bazel-bin/src/main/java/com/example/cmdline/runner
Hi!
See the build encyclopedia for more visibility options.

Deploying

If you look at the contents of bazel-bin/src/main/java/com/example/cmdline/runner.jar, you can see that it only contains Runner.class, not its dependencies (Greeting.class):

$ jar tf bazel-bin/src/main/java/com/example/cmdline/runner.jar
META-INF/
META-INF/MANIFEST.MF
com/
com/example/
com/example/cmdline/
com/example/cmdline/Runner.class
This works for running locally (the runner script Bazel generates adds the greeter jar to the classpath) but will not work if we want to copy runner.jar to another machine and use it as a standalone binary. To build a self-contained jar that can be deployed, build runner_deploy.jar (or, more generally, <target-name>_deploy.jar):

$ bazel build //src/main/java/com/example/cmdline:runner_deploy.jar
INFO: Found 1 target...
Target //src/main/java/com/example/cmdline:runner_deploy.jar up-to-date:
  bazel-bin/src/main/java/com/example/cmdline/runner_deploy.jar
INFO: Elapsed time: 1.700s, Critical Path: 0.23s
runner_deploy.jar will contain all of its dependencies.
```
