# Name: Kevin Eguida 
# University ID: 117911411 
# Directory ID: keguida 

is_palindrome:

    # PROLOGUE
    subu    $sp,        $sp,        8                  # expand stack by 8 bytes
    sw      $ra,        8($sp)                         # push $ra (ret addr, 4 bytes)
    sw      $fp,        4($sp)                         # push $fp (4 bytes)
    addu    $fp,        $sp,        8                  # set $fp to saved $ra

    # save $s1 and $s2 on stack before using them
    subu    $sp,        $sp,        8                  # expand stack by 8 bytes
    sw      $s2,      8($sp)                           # save $s2 on stack
    sw      $s1,      4($sp)                           # save $s1 on stack
                   
    jal strlen                                         # v0 = strlen(a0), a0 is a pointer to the first charcter of the string

    move    $t0,        $v0                            # t0 = strlen(a0)
    div     $t0,        $t0,        2                  # t0 = strlen(a0) / 2
    li      $t1,        0                              # use t1 for i, we start the looop at i = 0
    la      $s1,       ($a0)                           # s1 points to the first character of the string
    la      $s2,       ($a0)                           # s2 points to the first charcter of the string
    addu    $s2,        $s2,        $v0                # s2 = a0 + strlen(a0), s2 points to the null byte of the string
    subu    $s2,        $s2,        1                  # s2 = a0 + strlen(a0) - 1, s2 points to the character before the null byte
    
is_palindrome_loop:
    
    lb      $t2,       ($s1)                           # use t2 for string[i]
    lb      $t3,       ($s2)                           # use t3 for string[len - 1 - i] 
    beq     $t1,        $t0,        is_palindrome_ret1 # if i == strlen(a0) / 2, stop the loop and return 1
    bne     $t2,        $t3,        is_palindrome_ret0 # if a0[i] != a0[t0 - 1 - i] return 0
    
    # increment i
    addu    $t1,        $t1,        1                  # i ++
    addu    $s1,        $s1,        1
    subu    $s2,        $s2,        1
    
    j       is_palindrome_loop
    
is_palindrome_ret0:

    li      $v0,        0                              # rval = 0
    j       is_palindrome_end
    
is_palindrome_ret1:

    li      $v0,        1                              # rval = 1
 
is_palindrome_end:

    #restores the args
    lw      $s1,        4($sp)                         # restore $s1
    lw      $s2,        8($sp)                         # restore $s2

    # EPILOGUE
    move    $sp,         $fp                           # restore $sp
    lw      $ra,        ($fp)                          # restore saved $ra
    lw      $fp,        -4($sp)                        # restore saved $fp
    jr      $ra                                        # return
    

############## strlen function #########################

strlen:

    # PROLOGUE
    subu    $sp,        $sp,        8                  # expand stack by 8 bytes
    sw      $ra,        8($sp)                         # push $ra (ret addr, 4 bytes)
    sw      $fp,        4($sp)                         # push $fp (4 bytes)
    addu    $fp,        $sp,        8                  # set $fp to saved $ra

    # save $s1 on stack before using it
    subu    $sp,        $sp,        4                  # expand stack by 8 bytes
    sw      $s1,        4($sp)                         # save $s1 on stack
    
    la      $s1,        ($a0)                          # use s1 as a pointer to the first character of the string
    li      $t0,        0                              # use t0 to count how many character the string has
    
strlen_loop:

    lb      $t1,        ($s1)                          # t1 = string[i]
    beqz    $t1,        strlen_end                     # if string[i] == '\0' stop loop
    addu    $s1,        $s1,         1                 # i ++
    add     $t0,        $t0,         1                 # counter ++
    j       strlen_loop
    
    
strlen_end:
    move    $v0,        $t0                            # v0 = counter
    lw      $s1,        4($sp)                         # restore $s1
    # EPILOGUE
    move    $sp,        $fp                            # restore $sp
    lw      $ra,        ($fp)                          # restore saved $ra
    lw      $fp,        -4($sp)                        # restore saved $fp
    jr      $ra                                        # return