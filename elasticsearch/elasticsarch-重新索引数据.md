```
https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-reindex.html

POST _reindex
{
  "source": {
    "index": "twitter"
  },
  "dest": {
    "index": "new_twitter",
    "op_type": "create"
  }
}
```
