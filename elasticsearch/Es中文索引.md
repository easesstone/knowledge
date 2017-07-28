```shell 
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
                    "type" : "string",
                    "analyzer" : "ik_max_word"
                },
				"sex" : {
                    "type" : "long"
                },
				"reson" : {
                    "type" : "string",
                    "analyzer" : "ik_max_word"
                },
				"pkey" : {
                    "type" : "string",
                    "analyzer" : "ik_max_word"
                },
				"tag" : {
                    "type" : "string",
                    "analyzer" : "ik_max_word"
                },
				"creator" : {
                    "type" : "string",
                    "analyzer" : "ik_max_word"
                },
				"cphone" : {
                    "type" : "string",
                    "analyzer" : "ik_max_word"
                },
				"platformid" : {
                    "type" : "string",
                    "analyzer" : "ik_max_word"
                },
				"feature" : {
                    "type" : "string",
                    "analyzer" : "ik_max_word"
                },
				"createtime" : {
                    "type" : "date"
                },
				"updatetime" : {
                    "type" : "date"
                }	
            }
        }
    }
}'
```
