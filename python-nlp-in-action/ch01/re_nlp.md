# Regular Expression NLO

'''
$> python
Python 2.7.13 (default, Jan  3 2017, 12:39:21)
[GCC 4.2.1 Compatible Apple LLVM 8.0.0 (clang-800.0.42.1)] on darwin
Type "help", "copyright", "credits" or "license" for more information.
>>> import re
>>> re_greeting = re.compile(r"[^a-z]*([y]o|[h']?ello|ok|hey|(good[ ])?(morn[gin']{0,3}|afternoon|even[gin']{0,3}))[\s,;:]{1,3}([a-z]{1,20})", flags=re.IGNORECASE)
>>> re_greeting.match('Hello Rosa')
<_sre.SRE_Match object at 0x10a280d50>
>>> re_greeting.match('Hello Rosa').groups()
('Hello', None, None, 'Rosa')
>>> re_greeting.match("Good morning Rosa")
<_sre.SRE_Match object at 0x10a280d50>
>>> re_greeting.match("Good Norning Rosa")
>>> re_greeting.match("Good Morn'n Rosa")
<_sre.SRE_Match object at 0x10a280df8>
>>> re_greeting.match("yo Rosa")
<_sre.SRE_Match object at 0x10a280d50>
'''
