curl -XPUT 'ldl:9200/objectinfo?pretty' -H 'Content-Type: application/json' -d'
{
    "mappings" : {
        "person" : {
            "properties" : {
                "creator" : {
                    "type" : "string",
                    "index" : "not_analyzed" 
                }
            }
        }
    }

}
'
