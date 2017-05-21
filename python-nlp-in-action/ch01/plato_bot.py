#!/bin/python
"""
The simplest chat bot.
Recognises a 'greeting based input' using regular expressions.
Saye 'bye' to exit.
"""
from __future__ import print_function
import re
import sys

BOTNAME = "plato-bot"

RE_GREETING = re.compile(
    r"[^a-z]*([y]o|[h']?ello|ok|hey|(good[])?(morn[gin']{0,3}|afternoon|even[gin']{0,3}))[\s,;:]{1,3}([a-z]{1,20})",
    flags=re.IGNORECASE)

# terminal colours
RED = "\033[1;31m"
BLUE = "\033[1;34m"
CYAN = "\033[1;36m"
GREEN = "\033[0;32m"
YELLOW= "\033[0;33m"
RESET = "\033[0;0m"
BOLD = "\033[;1m"
REVERSE = "\033[;7m"

def longest(str1, str2):
    """ Get the length of the longest input string"""
    len1 = len(str1)
    len2 = len(str2)
    return len1 if len1 > len2 else len2

def repl(username):
    """ repl loop """
    padding = longest(username, BOTNAME)
    user_prompt = username.ljust(padding) + " > "
    bot_prompt = BOTNAME.ljust(padding) + " > "

    print("{}{}{}I am sleepy...".format(BLUE, bot_prompt, RESET))
    keep_chatting = True
    while keep_chatting:
        words = raw_input("{}{}{}".format(RED, user_prompt, RESET))
        if words.lower() != "bye":
            matches = RE_GREETING.match(words)
            if matches is not None:
                groups = matches.groups()
                greeting = groups[0]
                if greeting:
                    print("{}{}{}{}".format(BLUE, bot_prompt, RESET, greeting))
            else:
                print("{}{}{}I am sleepy...".format(BLUE, bot_prompt, RESET))
        else:
            print("{}{}{}Bye!".format(BLUE, bot_prompt, RESET))
            keep_chatting = False
    return keep_chatting

def main(argv):
    """
    Start the chat bot.
    """
    print("{}Wake-up Plato-Bot!{}".format(BOLD, RESET))
    username = "user"
    if len(argv) == 2:
        username = argv[1]
    repl(username)
    print("{}...zzzZZZ{}".format(BOLD, RESET))
    return 0

if __name__ == "__main__":
    main(sys.argv)
