# Name: Kevin Eguida 
# University ID: 117911411 
# Directory ID: keguida 

reverse_prefix_sum:
    # PROLOGUE
    subu    $sp,        $sp,        8                        # expand stack by 8 bytes
    sw      $ra,        8($sp)                               # push $ra (ret addr, 4 bytes)
    sw      $fp,        4($sp)                               # push $fp (4 bytes)
    addu    $fp,        $sp,        8                        # set $fp to saved $ra
    
    # save $s1 and $s2 on stack before using them
    subu    $sp,        $sp,        8                        # expand stack by 8 bytes
    sw      $s2,        8($sp)                               # save $s2 on stack
    sw      $s1,        4($sp)                               # save $s1 on stack

    move    $s1,        $a0                                  # stores the address of $a0 = arr in $s1
    lw      $s2,        ($a0)                                # s2 = *arr
    
    beq     $s2,        -1,         reverse_prefix_sum_ret_0 # if *arr == -1, return 0
    
    addu    $a0,        $a0,        4                        # $a0 = arr + 1
    jal     reverse_prefix_sum                               # $v0 = reverse_prefix_sum(arr + 1)
    move    $a0,        $s1                                  # restore the value of $a
    addu    $v0,        $s2,        $v0                      # return r = *arr + reverse_prefix_sum(arr + 1)
    sw      $v0,        ($a0)                                # *arr = r
    
    j reverse_prefix_sum_end

reverse_prefix_sum_ret_0:
    li      $v0,        0                                    # return 0

reverse_prefix_sum_end:
    lw      $s1,        4($sp)                                 # restore $s1
    lw      $s2,        8($sp)                                 # restore $s2
    # EPILOGUE
    move    $sp,        $fp                                  # restore $sp
    lw      $ra,        ($fp)                                # restore saved $ra
    lw      $fp,        -4($sp)                              # restore saved $fp
    jr      $ra                                              # return