module t {
    pure val c = Map(1 -> {cod: "adad", lin: "rust"},2 -> {cod: "adad", lin: "c++"},3 -> {cod: "adad", lin: "c++"})

    pure def t() = {
        c.keys().forall(k => c.get(k).lin == "c++" implies eventually(c.get(k).lin == "rust"))
    }
    }