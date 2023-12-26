# Name: Kevin Eguida 
# University ID: 117911411 
# Directory ID: keguida 

collatz:
    # PROLOGUE
    subu    $sp,        $sp,        8   # expand stack by 8 bytes
    sw      $ra,        8($sp)          # push $ra (ret addr, 4 bytes)
    sw      $fp,        4($sp)          # push $fp (4 bytes)
    addu    $fp,        $sp,        8   # set $fp to saved $ra


    beq     $s1,       1, collatz_ret_d # if n == 1, return d
    
    # save $s1 and $s2 on stack before using them
    subu    $sp,        $sp,        8   # expand stack by 8 bytes
    sw      $s2,        8($sp)          # save $s2 on stack
    sw      $s1,        4($sp)          # save $s1 on stack
    
    move    $t0,        $s1             # $t0 = n
    rem     $t0,        $t0,        2   # $t0 = n % 2
    beq     $t0,        1, collatz_odd  # if n % 2 == 1, return collatz(3 * n + 1, d + 1)
    
collatz_even:

    div     $s1,        $s1,        2   # n = n / 2               
    add     $s2,        $s2,        1   # d = d + 1
    
    jal     collatz                     # collatz(n / 2, d + 1)
    
    #restores the args
    lw      $s1,        4($sp)          # restore $s1
    lw      $s2,        8($sp)          # restore $s2

    j collatz_end

   
collatz_odd:

    mul     $s1,        $s1,        3   # n = n * 3
    add     $s1,        $s1,        1   # n = n + 1                   
    add     $s2,        $s2,        1   # d = d + 1
    
    jal     collatz                     # collatz(3 * n + 1, d + 1)

    #restores the args
    lw      $s1,        4($sp)          # restore $s1
    lw      $s2,        8($sp)          # restore $s2
    
    j collatz_end

collatz_ret_d:

    move    $v0,        $s2             # places d in $v0 and returns it

collatz_end:

    # EPILOGUE
    move    $sp,        $fp             # restore $sp
    lw      $ra,        ($fp)           # restore saved $ra
    lw      $fp,        -4($sp)         # restore saved $fp
    jr      $ra                         # return
