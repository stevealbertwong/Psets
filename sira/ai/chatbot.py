#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
see: https://github.com/api-ai/api-ai-python/blob/master/examples/slotfilling_example/slotfilling_example.py
"""
import os.path
import sys

try:
    import apiai
except ImportError:
    sys.path.append(
        os.path.join(os.path.dirname(os.path.realpath(__file__)), os.pardir)
    )
    import apiai

import json


CLIENT_ACCESS_TOKEN = 'e915f54768b14ed785285ebdff03c3f9'


def main():
    ai = apiai.ApiAI(CLIENT_ACCESS_TOKEN)
    user_input = ""

    #loop the queries to API.AI so we can have a conversation client-side
    while user_input != 'exit':

        user_input = raw_input("Steven:")

        request = ai.text_request()
        request.query = user_input
        response = json.loads(request.getresponse().read())

        result = response["result"]

        print(u"Cantonese Elderly Search bot %s" % response['result']['fulfillment']['speech'])

        # fulfillment = result["fulfillment"]
        # print "bot:" + fulfillment['speech']




if __name__ == '__main__':
    main()