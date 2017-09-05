http://blog.csdn.net/u011403655/article/details/71107415
```
Caused by: NotSerializableExceptionWrapper[: Fielddata is disabled on text fields by default. 
Set fielddata=true on [name] in order to load fielddata in memory by uninverting the inverted index.
Note that this can however use significant memory. Alternatively use a keyword field instead.]; nested:
IllegalArgumentException[Fielddata is disabled on text fields by default. Set fielddata=true on [name] 
in order to load fielddata in memory by uninverting the inverted index. Note that this can however use 
significant memory. Alternatively use a keyword field instead.];
        at org.elasticsearch.ElasticsearchException.guessRootCauses(ElasticsearchException.java:618)
        at org.elasticsearch.action.search.SearchPhaseExecutionException.guessRootCauses(SearchPhaseExecutionException.java:170)
        at org.elasticsearch.action.search.SearchPhaseExecutionException.getCause(SearchPhaseExecutionException.java:111)
        at org.elasticsearch.ElasticsearchException.writeTo(ElasticsearchException.java:285)
        at org.elasticsearch.action.search.SearchPhaseExecutionException.writeTo(SearchPhaseExecutionException.java:61)
        at org.elasticsearch.common.io.stream.StreamOutput.writeException(StreamOutput.java:838)
        at org.elasticsearch.ElasticsearchException.writeTo(ElasticsearchException.java:285)
        at org.elasticsearch.transport.ActionTransportException.writeTo(ActionTransportException.java:64)
        at org.elasticsearch.common.io.stream.StreamOutput.writeException(StreamOutput.java:838)
        at org.elasticsearch.transport.TcpTransport.sendErrorResponse(TcpTransport.java:1147)
        at org.elasticsearch.transport.TcpTransportChannel.sendResponse(TcpTransportChannel.java:76)
        at org.elasticsearch.transport.DelegatingTransportChannel.sendResponse(DelegatingTransportChannel.java:70)
        at org.elasticsearch.transport.RequestHandlerRegistry$TransportChannelWrapper.sendResponse(RequestHandlerRegistry.java:123)
        at org.elasticsearch.action.support.HandledTransportAction$TransportHandler$1.onFailure(HandledTransportAction.java:77)
        at org.elasticsearch.action.search.AbstractSearchAsyncAction.raisePhaseFailure(AbstractSearchAsyncAction.java:219)
        ... 35 more
Caused by: java.lang.IllegalArgumentException: Fielddata is disabled on text fields by default. 
Set fielddata=true on [name] in order to load fielddata in memory by uninverting the inverted index.
Note that this can however use significant memory. Alternatively use a keyword field instead.
        at org.elasticsearch.index.mapper.TextFieldMapper$TextFieldType.fielddataBuilder(TextFieldMapper.java:336)
        at org.elasticsearch.index.fielddata.IndexFieldDataService.getForField(IndexFieldDataService.java:111)
        at org.elasticsearch.index.query.QueryShardContext.getForField(QueryShardContext.java:166)
        at org.elasticsearch.search.sort.FieldSortBuilder.build(FieldSortBuilder.java:277)
        at org.elasticsearch.search.sort.SortBuilder.buildSort(SortBuilder.java:156)
        at org.elasticsearch.search.SearchService.parseSource(SearchService.java:630)
        at org.elasticsearch.search.SearchService.createContext(SearchService.java:481)
        at org.elasticsearch.search.SearchService.createAndPutContext(SearchService.java:457)
        at org.elasticsearch.search.SearchService.executeQueryPhase(SearchService.java:253)
        at org.elasticsearch.action.search.SearchTransportService$6.messageReceived(SearchTransportService.java:330)
        at org.elasticsearch.action.search.SearchTransportService$6.messageReceived(SearchTransportService.java:327)
        at org.elasticsearch.transport.RequestHandlerRegistry.processMessageReceived(RequestHandlerRegistry.java:69)
        at org.elasticsearch.transport.TcpTransport$RequestHandler.doRun(TcpTransport.java:1544)
        at org.elasticsearch.common.util.concurrent.ThreadContext$ContextPreservingAbstractRunnable.doRun(ThreadContext.java:638)
        at org.elasticsearch.common.util.concurrent.AbstractRunnable.run(AbstractRunnable.java:37)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
```
