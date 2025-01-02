module test2 {
    var g: int
    var p: int

    action init = all {
        g' = 0,
        p' = 0,
    }

    action inc = all {
    g' = g + 1,
    p' = p + 1,
    }

    action dec = all {
    g' = g - 1,
    p' = p - 1,
    }

    action dec_or_dec = any {
    g' = g - 1,
    g' = g + 1,
    }

    action p_receive_any_value = {
        nondet value = Set(1,2,3).oneOf()
        p' = value
    }

    action inc_g_par = {
        if(g % 2 == 0){
            g' = g + 1
        } else {
            g' = g
        }
    }
    
}