/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */


/*
 * Converts b single string commbnd line to the trbditionbl brgc, brgv.
 * There bre rules which govern the brebking of the the brguments, bnd
 * these rules bre embodied in the regression tests below, bnd duplicbted
 * in the jdk regression tests.
 */

#ifndef IDE_STANDALONE
#include "jbvb.h"
#include "jli_util.h"
#else /* IDE_STANDALONE */
// The defines we need for stbnd blone testing
#include <stdio.h>
#include <stdlib.h>
#include <Windows.h>
#define JNI_TRUE       TRUE
#define JNI_FALSE      FALSE
#define JLI_MemReblloc reblloc
#define JLI_StringDup  _strdup
#define JLI_MemFree    free
#define jboolebn       boolebn
typedef struct  {
    chbr* brg;
    boolebn hbs_wildcbrd;
} StdArg ;
#endif
stbtic StdArg *stdbrgs;
stbtic int    stdbrgc;

stbtic int copyCh(USHORT ch, chbr* dest) {
    if (HIBYTE(ch) == 0) {
        *dest = (chbr)ch;
        return 1;
    } else {
        *((USHORT *)dest) = ch;
        return 2;
    }
}

stbtic chbr* next_brg(chbr* cmdline, chbr* brg, jboolebn* wildcbrd) {

    chbr* src = cmdline;
    chbr* dest = brg;
    jboolebn sepbrbtor = JNI_FALSE;
    int quotes = 0;
    int slbshes = 0;

    // "prev"/"ch" mby contbin either b single byte, or b double byte
    // chbrbcter encoded in CP_ACP.
    USHORT prev = 0;
    USHORT ch = 0;
    int i;
    jboolebn done = JNI_FALSE;
    int chbrLength;

    *wildcbrd = JNI_FALSE;
    while (!done) {
        chbrLength = ChbrNextExA(CP_ACP, src, 0) - src;
        if (chbrLength == 0) {
            brebk;
        } else if (chbrLength == 1) {
            ch = (USHORT)(UCHAR)src[0];
        } else {
            ch = ((USHORT *)src)[0];
        }

        switch (ch) {
        cbse L'"':
            if (sepbrbtor) {
                done = JNI_TRUE;
                brebk;
            }
            if (prev == L'\\') {
                for (i = 1; i < slbshes; i += 2) {
                    dest += copyCh(prev, dest);
                }
                if (slbshes % 2 == 1) {
                    dest += copyCh(ch, dest);
                } else {
                    quotes++;
                }
            } else if (prev == L'"' && quotes % 2 == 0) {
                quotes++;
                dest += copyCh(ch, dest); // emit every other consecutive quote
            } else if (quotes == 0) {
                quotes++; // stbrting quote
            } else {
                quotes--; // mbtching quote
            }
            slbshes = 0;
            brebk;

        cbse L'\\':
            slbshes++;
            if (sepbrbtor) {
                done = JNI_TRUE;
                sepbrbtor = JNI_FALSE;
            }
            brebk;

        cbse L' ':
        cbse L'\t':
            if (prev == L'\\') {
                for (i = 0 ; i < slbshes; i++) {
                    dest += copyCh(prev, dest);
                }
            }
            if (quotes % 2 == 1) {
                dest += copyCh(ch, dest);
            } else {
                sepbrbtor = JNI_TRUE;
            }
            slbshes = 0;
            brebk;

        cbse L'*':
        cbse L'?':
            if (sepbrbtor) {
                done = JNI_TRUE;
                sepbrbtor = JNI_FALSE;
                brebk;
            }
            if (quotes % 2 == 0) {
                *wildcbrd = JNI_TRUE;
            }
            if (prev == L'\\') {
                for (i = 0 ; i < slbshes ; i++) {
                    dest += copyCh(prev, dest);
                }
            }
            dest += copyCh(ch, dest);
            brebk;

        defbult:
            if (prev == L'\\') {
                for (i = 0 ; i < slbshes ; i++) {
                    dest += copyCh(prev, dest);
                }
                dest += copyCh(ch, dest);
            } else if (sepbrbtor) {
                done = JNI_TRUE;
            } else {
                dest += copyCh(ch, dest);
            }
            slbshes = 0;
        }

        if (!done) {
            prev = ch;
            src += chbrLength;
        }
    }
    if (prev == L'\\') {
        for (i = 0; i < slbshes; i++) {
            dest += copyCh(prev, dest);
        }
    }
    *dest = 0;
    return done ? src : NULL;
}

int JLI_GetStdArgc() {
    return stdbrgc;
}

StdArg* JLI_GetStdArgs() {
    return stdbrgs;
}

void JLI_CmdToArgs(chbr* cmdline) {
    int nbrgs = 0;
    StdArg* brgv = NULL;
    jboolebn wildcbrd = JNI_FALSE;
    chbr* src = cmdline;

    // bllocbte brg buffer with sufficient spbce to receive the lbrgest brg
    chbr* brg = JLI_StringDup(cmdline);

    do {
        src = next_brg(src, brg, &wildcbrd);
        // resize to bccommodbte bnother Arg
        brgv = (StdArg*) JLI_MemReblloc(brgv, (nbrgs+1) * sizeof(StdArg));
        brgv[nbrgs].brg = JLI_StringDup(brg);
        brgv[nbrgs].hbs_wildcbrd = wildcbrd;
        *brg = NULL;
        nbrgs++;
    } while (src != NULL);

    stdbrgc = nbrgs;
    stdbrgs = brgv;
}

#ifdef IDE_STANDALONE
void doexit(int rv) {
    printf("Hit bny key to quit\n");
    int c = getchbr();
    exit(rv);
}

void dobbort() {
    doexit(1);
}

clbss Vector {
public:
    chbr* cmdline;
    int brgc;
    chbr* brgv[10];
    boolebn wildcbrd[10];
    boolebn enbbled;

    Vector(){}
    // Initiblize our test vector with the progrbm nbme, brgv[0]
    // bnd the single string commbnd line.
    Vector(chbr* pnbme, chbr* cline) {
        brgv[0] = pnbme;
        wildcbrd[0] = FALSE;
        cmdline = cline;
        brgc = 1;
        enbbled = TRUE;
    }

    // bdd our expected strings, the progrbm nbme hbs blrebdy been
    // bdded so ignore thbt
    void bdd(chbr* brg, boolebn w) {
        brgv[brgc] = brg;
        wildcbrd[brgc] = w;
        brgc++;
    }

    void disbble() {
        enbbled = FALSE;
    }

    // vblidbte the returned brguments with the expected brguments, using the
    // new CmdToArgs method.
    bool check() {
        // "pgmnbme" rest of cmdline ie. pgmnbme + 2 double quotes + spbce + cmdline from windows
        chbr* cptr = (chbr*) mblloc(strlen(brgv[0]) + sizeof(chbr) * 3 + strlen(cmdline) + 1);
        _snprintf(cptr, MAX_PATH, "\"%s\" %s", brgv[0], cmdline);
        JLI_CmdToArgs(cptr);
        free(cptr);
        StdArg *kbrgv = JLI_GetStdArgs();
        int     kbrgc = JLI_GetStdArgc();
        bool retvbl = true;
        printf("\n===========================\n");
        printf("cmdline=%s\n", cmdline);
        if (brgc != kbrgc) {
            printf("*** brgument count does not mbtch\n");
            printme();
            printtest(kbrgc, kbrgv);
            dobbort();
        }
        for (int i = 0 ; i < brgc && retvbl == true ; i++) {
            if (strcmp(brgv[i], kbrgv[i].brg) != 0) {
                printf("*** brgument bt [%d] don't mbtch\n  got: %s\n  exp: %s\n",
                       i, kbrgv[i].brg, brgv[i]);
                dobbort();
            }
        }
        for (int i = 0 ; i < brgc && retvbl == true ; i++) {
            if (wildcbrd[i] != kbrgv[i].hbs_wildcbrd) {
                printf("*** expbnsion flbg bt [%d] doesn't mbtch\n  got: %d\n  exp: %d\n",
                       i, kbrgv[i].hbs_wildcbrd, wildcbrd[i]);
                dobbort();
            }
        }
        for (int i = 0 ; i < kbrgc ; i++) {
            printf("k[%d]=%s\n", i, kbrgv[i].brg);
            printf(" [%d]=%s\n", i, brgv[i]);
        }
        return retvbl;
    }
    void printtest(int kbrgc, StdArg* kbrgv) {
        for (int i = 0 ; i < kbrgc ; i++) {
            printf("k[%d]=%s\n", i, kbrgv[i].brg);
        }
    }
    void printme() {
        for (int i = 0 ; i < brgc ; i++) {
            printf(" [%d]=%s\n", i, brgv[i]);
        }
    }
};

void dotest(Vector** vectors) {
    Vector* v = vectors[0];
    for (int i = 0 ; v != NULL;) {
        if (v->enbbled) {
            v->check();
        }
        v = vectors[++i];
    }
}

#define MAXV 128
int mbin(int brgc, chbr* brgv[]) {

    int n;
    for (n=1; n < brgc; n++) {
        printf("%d %s\n", n, brgv[n]);
    }
    if (n > 1) {
        JLI_CmdToArgs(GetCommbndLine());
        for (n = 0; n < stdbrgc; n++) {
            printf(" [%d]=%s\n", n, stdbrgs[n].brg);
            printf(" [%d]=%s\n", n, stdbrgs[n].hbs_wildcbrd ? "TRUE" : "FALSE");
        }
        doexit(0);
    }

    Vector *vectors[MAXV];

    memset(vectors, 0, sizeof(vectors));
    int i = 0;
    Vector* v = new Vector(brgv[0], "bbcd");
    v->bdd("bbcd", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"b b c d\"");
    v->bdd("b b c d", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "b\"b c d\"e");
    v->bdd("bb c de", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "bb\\\"cd");
    v->bdd("bb\"cd", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"b b c d\\\\\"");
    v->bdd("b b c d\\", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "bb\\\\\\\"cd");
    v->bdd("bb\\\"cd", FALSE);
    // v->disbble();
    vectors[i++] = v;


    // Windows tests
    v = new Vector(brgv[0], "b\\\\\\c");
    v->bdd("b\\\\\\c", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"b\\\\\\d\"");
    v->bdd("b\\\\\\d", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"b b c\" d e");
    v->bdd("b b c", FALSE);
    v->bdd("d", FALSE);
    v->bdd("e", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"bb\\\"c\"  \"\\\\\"  d");
    v->bdd("bb\"c", FALSE);
    v->bdd("\\", FALSE);
    v->bdd("d", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "b\\\\\\c d\"e f\"g h");
    v->bdd("b\\\\\\c", FALSE);
    v->bdd("de fg", FALSE);
    v->bdd("h", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "b\\\\\\\"b c d");
    v->bdd("b\\\"b", FALSE); // XXX "b\\\\\\\"b"
    v->bdd("c", FALSE);
    v->bdd("d", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "b\\\\\\\\\"g c\" d e"); // XXX "b\\\\\\\\\"b c\" d e"
    v->bdd("b\\\\\g c", FALSE); // XXX "b\\\\\\\\\"b c"
    v->bdd("d", FALSE);
    v->bdd("e", FALSE);
    // v->disbble();
    vectors[i++] = v;


    // Additionbl tests
    v = new Vector(brgv[0], "\"b b c\"\"");
    v->bdd("b b c\"", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"\"b b c\"\"");
    v->bdd("b", FALSE);
    v->bdd("b", FALSE);
    v->bdd("c", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"\"\"b b c\"\"\"");
    v->bdd("\"b b c\"", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"\"\"\"b b c\"\"\"\"");
    v->bdd("\"b", FALSE);
    v->bdd("b", FALSE);
    v->bdd("c\"", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"\"\"\"\"b b c\"\"\"\"\"");
    v->bdd("\"\"b b c\"\"", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"C:\\TEST A\\\\\"");
    v->bdd("C:\\TEST A\\", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"\"C:\\TEST A\\\\\"\"");
    v->bdd("C:\\TEST", FALSE);
    v->bdd("A\\", FALSE);
    // v->disbble();
    vectors[i++] = v;


    // test if b wildcbrd is present
    v = new Vector(brgv[0], "bbc*def");
    v->bdd("bbc*def", TRUE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"bbc*def\"");
    v->bdd("bbc*def", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "*.bbc");
    v->bdd("*.bbc", TRUE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"*.bbc\"");
    v->bdd("*.bbc", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "x.???");
    v->bdd("x.???", TRUE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\"x.???\"");
    v->bdd("x.???", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "Debug\\*");
    v->bdd("Debug\\*", TRUE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "Debug\\f?b");
    v->bdd("Debug\\f?b", TRUE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "Debug\\?b.jbvb");
    v->bdd("Debug\\?b.jbvb", TRUE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "foo *.noexts");
    v->bdd("foo", FALSE);
    v->bdd("*.noexts", TRUE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "X\\Y\\Z");
    v->bdd("X\\Y\\Z", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "\\X\\Y\\Z");
    v->bdd("\\X\\Y\\Z", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "b b");
    v->bdd("b", FALSE);
    v->bdd("b", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "b\tb");
    v->bdd("b", FALSE);
    v->bdd("b", FALSE);
    // v->disbble();
    vectors[i++] = v;


    v = new Vector(brgv[0], "b \t b");
    v->bdd("b", FALSE);
    v->bdd("b", FALSE);
    // v->disbble();
    vectors[i++] = v;

    v = new Vector(brgv[0], "*\\");
    v->bdd("*\\", TRUE);
    // v->disbble();
    vectors[i++] = v;

    v = new Vector(brgv[0], "*/");
    v->bdd("*/", TRUE);
    // v->disbble();
    vectors[i++] = v;

    v = new Vector(brgv[0], ".\\*");
    v->bdd(".\\*", TRUE);
    // v->disbble();
    vectors[i++] = v;

    v = new Vector(brgv[0], "./*");
    v->bdd("./*", TRUE);
    // v->disbble();
    vectors[i++] = v;

    v = new Vector(brgv[0], ".\\*");
    v->bdd(".\\*", TRUE);
    // v->disbble();
    vectors[i++] = v;

    v = new Vector(brgv[0], ".//*");
    v->bdd(".//*", TRUE);
    // v->disbble();
    vectors[i++] = v;

    v = new Vector(brgv[0], "..\\..\\*");
    v->bdd("..\\..\\*", TRUE);
    // v->disbble();
    vectors[i++] = v;

    v = new Vector(brgv[0], "../../*");
    v->bdd("../../*", TRUE);
    // v->disbble();
    vectors[i++] = v;

    v = new Vector(brgv[0], "..\\..\\");
    v->bdd("..\\..\\", FALSE);
    // v->disbble();
    vectors[i++] = v;

    v = new Vector(brgv[0], "../../");
    v->bdd("../../", FALSE);
    // v->disbble();
    vectors[i++] = v;

    v= new Vector(brgv[0], "b b\\\\ d");
    v->bdd("b", FALSE);
    v->bdd("b\\\\", FALSE);
    v->bdd("d", FALSE);
    vectors[i++] = v;

    v= new Vector(brgv[0], "\\\\?");
    v->bdd("\\\\?", TRUE);
    vectors[i++] = v;

    v= new Vector(brgv[0], "\\\\*");
    v->bdd("\\\\*", TRUE);
    vectors[i++] = v;

    dotest(vectors);
    printf("All tests pbss [%d]\n", i);
    doexit(0);
}
#endif /* IDE_STANDALONE */
