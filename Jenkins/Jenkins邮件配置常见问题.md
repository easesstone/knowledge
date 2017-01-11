### 1)Client does not have permissions to send as this sender
```
原因：管理员邮箱中少配置了***@huawei.com(必须是可用的邮箱。)
Failed to send out e-mail
com.sun.mail.smtp.SMTPSendFailedException: 550 5.7.1 Client does not have permissions to send as this sender
at com.sun.mail.smtp.SMTPTransport.issueSendCommand(SMTPTransport.java:2057)
	at com.sun.mail.smtp.SMTPTransport.finishData(SMTPTransport.java:1862)
	at com.sun.mail.smtp.SMTPTransport.sendMessage(SMTPTransport.java:1100)
	at javax.mail.Transport.send0(Transport.java:195)
	at javax.mail.Transport.send(Transport.java:124)
	at hudson.tasks.Mailer$DescriptorImpl.doSendTestMail(Mailer.java:522)
	at sun.reflect.GeneratedMethodAccessor265.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at org.kohsuke.stapler.Function$InstanceFunction.invoke(Function.java:298)
	at org.kohsuke.stapler.Function.bindAndInvoke(Function.java:161)
	at org.kohsuke.stapler.Function.bindAndInvokeAndServeResponse(Function.java:96)
	at org.kohsuke.stapler.MetaClass$1.doDispatch(MetaClass.java:121)
	at org.kohsuke.stapler.NameBasedDispatcher.dispatch(NameBasedDispatcher.java:53)
	at org.kohsuke.stapler.Stapler.tryInvoke(Stapler.java:746)
	at org.kohsuke.stapler.Stapler.invoke(Stapler.java:876)
	at org.kohsuke.stapler.MetaClass$6.doDispatch(MetaClass.java:249)
	at org.kohsuke.stapler.NameBasedDispatcher.dispatch(NameBasedDispatcher.java:53)
	at org.kohsuke.stapler.Stapler.tryInvoke(Stapler.java:746)
	at org.kohsuke.stapler.Stapler.invoke(Stapler.java:876)
	at org.kohsuke.stapler.Stapler.invoke(Stapler.java:649)
	at org.kohsuke.stapler.Stapler.service(Stapler.java:238)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:731)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:303)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)
	at hudson.util.PluginServletFilter$1.doFilter(PluginServletFilter.java:132)
	at hudson.util.PluginServletFilter.doFilter(PluginServletFilter.java:123)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)
	at hudson.security.csrf.CrumbFilter.doFilter(CrumbFilter.java:49)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)
	at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:84)
	at hudson.security.UnwrapSecurityExceptionFilter.doFilter(UnwrapSecurityExceptionFilter.java:51)
	at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	at jenkins.security.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:117)
	at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	at org.acegisecurity.providers.anonymous.AnonymousProcessingFilter.doFilter(AnonymousProcessingFilter.java:125)
	at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	at org.acegisecurity.ui.rememberme.RememberMeProcessingFilter.doFilter(RememberMeProcessingFilter.java:142)
	at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	at org.acegisecurity.ui.AbstractProcessingFilter.doFilter(AbstractProcessingFilter.java:271)
	at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	at jenkins.security.BasicHeaderProcessor.doFilter(BasicHeaderProcessor.java:93)
	at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	at org.acegisecurity.context.HttpSessionContextIntegrationFilter.doFilter(HttpSessionContextIntegrationFilter.java:249)
	at hudson.security.HttpSessionContextIntegrationFilter2.doFilter(HttpSessionContextIntegrationFilter2.java:67)
	at hudson.security.ChainedServletFilter$1.doFilter(ChainedServletFilter.java:87)
	at hudson.security.ChainedServletFilter.doFilter(ChainedServletFilter.java:76)
	at hudson.security.HudsonFilter.doFilter(HudsonFilter.java:171)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)
	at org.kohsuke.stapler.compression.CompressionFilter.doFilter(CompressionFilter.java:49)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)
	at hudson.util.CharacterEncodingFilter.doFilter(CharacterEncodingFilter.java:81)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)
	at org.kohsuke.stapler.DiagnosticThreadNameFilter.doFilter(DiagnosticThreadNameFilter.java:30)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:220)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:122)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:614)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:170)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)
	at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:957)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:116)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:423)
	at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1079)
	at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:620)
	at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:318)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.lang.Thread.run(Thread.java:745)

```

### 2) 用户名和密码是域账号。
### 3）其他选项。
```
Extended E-mail Notification
 	SMTP server		smtpscn.huawei.com
 	Default user E-mail suffix		@notesmail.huawei.com
 
高级...
Use SMTP Authentication	Help for feature: Use SMTP Authentication
 	User Name		域账号
 	Password		密码
 	Use SSL		
 	SMTP port		25
 	Charset		UTF-8
 	Default Content Type		HTML(text/html)
Use List-ID Email Header	
Add 'Precedence: bulk' Email Header	
 	Default Recipients		********
 	Reply To List		
 	Emergency reroute		
 	Excluded Recipients		
 	Default Subject		【Jenkins Build Inform】:$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!
 	Maximum Attachment Size		
 	Default Content	
			PROJECT NAME：$PROJECT_NAME<br/><hr/>
			BUILD NUMBER：$BUILD_NUMBER<br/><hr/>
			BUILD STATUS：$BUILD_STATUS<br/><hr/>
			BUILD CAUSE：${CAUSE}<br/><hr/>
			BUILD CONSOLE：<a href="${BUILD_URL}console">${BUILD_URL}console</a><br/><hr/>
			BUILD URL：<a href="$BUILD_URL">$BUILD_URL</a><br/><hr/>
			Check console output at $BUILD_URL to view the results.<br/><hr/>
			FAILED BUILDS:<br/><hr/>
			${BUILD_LOG_REGEX,regex="completed  : FAILURE",showTruncatedLines="false",substText=" completed  : FAILURE<br/><hr/> ",addNewline="true"}<br/><hr/>
			UNSTABLE BUILDS:<br/><hr/>
			${BUILD_LOG_REGEX,regex="completed  : UNSTABLE",showTruncatedLines="false",substText=" completed  :UNSTABLE<br/><hr/> ",addNewline="true"}<br/><hr/>
 	Default Pre-send Script	
 	Additional groovy classpath	
增加
Help for feature: Additional groovy classpath
Enable Debug Mode	Help for feature: Enable Debug Mode
Enable Security	Help for feature: Enable Security
Require Administrator for Template Testing	Help for feature: Require Administrator for Template Testing
Enable watching for jobs	Help for feature: Enable watching for jobs
 
Default Triggers...
 	Content Token Reference	
```
### 4) 具体工程中，只需要配置收件人的地址，类似：***@notesmail.huawei.com
