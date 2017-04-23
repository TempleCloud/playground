----------------------------------------------------------------------------------------------------

# LLDB Quick Reference

NB: Basically, the same as GDB except for some stupid long command names...

1.  __run [args]__             : Start your program with [args].
2.  __breakpoint set --name [file:]function__
                               : Set a break point at [file:]function. You can also use __b__. 
3.  __thread backtrace__       : Dump a backtrace of ehe current calling stack. Shorthand is __bt__. 
4.  __print expr__             : Print the value of expr. Shorthand is __p__.
5.  __continue__               : Continue running the program. Shorthand is __c__.
6.  __next__                   : Next line, but step over function calls. Shorthand is __n__.
7.  __step__                   : Next line, but step into function calls. Shorthand is __s__.
8.  __quit__                   : Exit LLDB.
9.  __help__                   : List the types of commands and get help.
10. __cd, pwd, make__          : Just like running these commands in your shell.
11. __shell__                  : Quickly start a shell so you can do other things.
12. __clear__                  : Clear a breakpoint.
13. __info break__             : Show information about breakpoints. 
14. __info watch__             : Show information about watchpoints. 
15. __attach pid__             : Attach to a running process so you can debug it.
16. __detach__                 : Detach from the process.
17. __list__                   : List out the next ten source lines. Add __a__ - to list the previous ten lines.
