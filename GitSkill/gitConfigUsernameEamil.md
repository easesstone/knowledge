## Command line instructions


### Git global setup

```
git config --global user.name "lidiliang WX355499"
git config --global user.email "lidiliang@huawei.com"
```

### Create a new repository

```
git clone git@code.huawei.com:lWX355499/testhello.git
cd testhello
touch README.md
git add README.md
git commit -m "add README"
git push -u origin master
```

### Existing folder or Git repository

```
cd existing_folder
git init
git remote add origin git@code.huawei.com:lWX355499/testhello.git
git add .
git commit
git push -u origin master
```
