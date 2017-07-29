```
curl -XDELETE 'ldl:9200/objectinfo?pretty' -H 'Content-Type: application/json'
curl -XPUT 'ldl:9200/objectinfo?pretty' -H 'Content-Type: application/json' -d'
{          
    "settings": {
        "number_of_shards": 1, 
        "analysis": {
            "filter": {
                "autocomplete_filter": { 
                    "type":     "edge_ngram",
                    "min_gram": 1,
                    "max_gram": 30
                }
          },
          "analyzer": {
                "autocomplete": {
                    "type":      "custom",
                    "tokenizer": "standard",
                    "filter": [
                        "lowercase",
                        "autocomplete_filter" 
                    ]
                    },
                    "ik" : {
                    "tokenizer" : "ik_max_word"
                    }
            }
        }
    }
}
'

curl -XPUT 'http://ldl:9200/objectinfo?pretty' -d '{
    "settings" : {
        "analysis" : {
            "analyzer" : {
                "ik" : {
                    "tokenizer" : "ik_max_word"
                }
            }
        }
    },
    "mappings" : {
        "article" : {
            "dynamic" : true,
            "properties" : {
                "name" : {
                    "type" : "string",
                    "analyzer" : "ik_max_word"
                },
                "idcard" : {
                    "type" : "string"
                },               
                "sex" : {
                    "type" : "long"
                },
                "reson" : {
                    "type" : "string",
                    "analyzer" : "ik_max_word"
                },
                "pkey" : {
                    "type" : "string"
                },
                "tag" : {
                    "type" : "string"           
                },
                "creator" : {
                    "type" : "string",
                    "analyzer" : "ik_max_word"
                },
                "cphone" : {
                    "type" : "string"
                },
                "platformid" : {                          
                    "type" : "string"
                },
                "feature" : {
                    "type" : "string"
                },
                "createtime" : {
                    "type" : "date",
                    "format": "yyyy-MM-dd HH:mm:ss"
                },
                "updatetime" : {
                    "type" : "date",
                    "format": "yyyy-MM-dd HH:mm:ss"
                }	
            }
        }
    }
}
'

curl -XPUT 'ldl:9200/nima_index?pretty' -H 'Content-Type: application/json' -d'
{                   
    "settings": {
        "analysis": {
            "filter": {
                "trigrams_filter": {
                    "type":     "ngram",
                    "min_gram": 1,
                    "max_gram": 20
                }
            },
            "analyzer": {
                "trigrams": {
                    "type":      "custom",
                    "tokenizer": "standard",
                    "filter":   [
                        "lowercase",
                        "trigrams_filter"
                    ]
                }
            }
        }
    },
    "mappings": {
        "my_type": {
            "properties": {
                "text": {
                    "type":     "string",
                    "analyzer": "trigrams" 
                }
            }
        }
    }
}
'


curl -XGET 'http://ldl:9200/nima_index/_analyze?pretty&analyzer=trigrams' -d '
11111111111111111111
'


```
