# Name: Kevin Eguida 
# University ID: 117911411 
# Directory ID: keguida 

isqrt:
    # PROLOGUE
    subu    $sp,        $sp,        8   # expand stack by 8 bytes
    sw      $ra,        8($sp)          # push $ra (ret addr, 4 bytes)
    sw      $fp,        4($sp)          # push $fp (4 bytes)
    addu    $fp,        $sp,        8   # set $fp to saved $ra

    # save $s1 and $s2 on stack before using them
    subu    $sp,        $sp,        8   # expand stack by 8 bytes
    sw      $s2,      8($sp)            # save $s2 on stack
    sw      $s1,      4($sp)            # save $s1 on stack
    
    blt     $a0,        2,  isqrt_ret_n # if n < 2, return n
    
    move    $s1,        $a0             # s1 = n
    srl     $a0,        $a0,        2   # a0 = n >> 2
    
    jal isqrt                           # v0 = isqrt(n >> 2)
    
    sll     $v0,        $v0,        1   # v0 = isqrt(n >> 2) << 1 = small
    add     $s2,        $v0,        1   # s2 = large = small + 1
    mul     $t0,        $s2,        $s2 # t0 = large * large
    
    ble     $t0,   $s1, isqrt_ret_large # if large * large <= n return large
    
isqrt_ret_small:
    b       isqrt_end                   # return small (v0 already contains small)
    
isqrt_ret_large:
    move    $v0,        $s2             # return large
    b       isqrt_end
    
isqrt_ret_n:
    move    $v0,        $a0             # return n
     
isqrt_end:

    #restores the args
    lw      $s1,        4($sp)          # restore $s1
    lw      $s2,        8($sp)          # restore $s2

    # EPILOGUE
    move    $sp,         $fp            # restore $sp
    lw      $ra,        ($fp)           # restore saved $ra
    lw      $fp,      -4($sp)           # restore saved $fp
    jr      $ra                         # return