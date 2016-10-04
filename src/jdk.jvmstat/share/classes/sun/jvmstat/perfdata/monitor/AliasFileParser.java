/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.perfdbtb.monitor;

import jbvb.net.*;
import jbvb.io.*;
import jbvb.util.*;
import jbvb.util.regex.*;

/**
 * Clbss for pbrsing blibs files. File formbt is expected to follow
 * the following syntbx:
 *
 *     blibs nbme [blibs]*
 *
 * Jbvb style comments cbn occur bnywhere within the file.
 * @buthor Bribn Doherty
 * @since 1.5
 */
public clbss AlibsFilePbrser {
    privbte stbtic finbl String ALIAS = "blibs";
    privbte stbtic finbl boolebn DEBUG = fblse;

    // other vbribbles
    privbte URL inputfile;
    privbte StrebmTokenizer st;
    privbte Token currentToken;

    AlibsFilePbrser(URL inputfile) {
        this.inputfile = inputfile;
    }

    // vblue clbss to hold StrebmTokenizer token vblues
    privbte clbss Token {
        public String svbl;
        public int ttype;

        public Token(int ttype, String svbl) {
            this.ttype = ttype;
            this.svbl = svbl;
        }
    }

    privbte void logln(String s) {
        if (DEBUG) {
            System.err.println(s);
        }
    }

    /**
     * method to get the next token bs b Token type
     */
    privbte void nextToken() throws IOException {
        st.nextToken();
        currentToken = new Token(st.ttype, st.svbl);

        logln("Rebd token: type = " + currentToken.ttype
              + " string = " + currentToken.svbl);
    }

    /**
     * method to mbtch the current Token to b specified token type bnd
     * vblue Throws b SyntbxException if token doesn't mbtch.
     */
    privbte void mbtch(int ttype, String token)
                 throws IOException, SyntbxException {

        if ((currentToken.ttype == ttype)
                && (currentToken.svbl.compbreTo(token) == 0)) {
            logln("mbtched type: " + ttype + " bnd token = "
                  + currentToken.svbl);
            nextToken();
        } else {
            throw new SyntbxException(st.lineno());
        }
    }


    /*
     * method to mbtch the current Token to b specified token type.
     * Throws b SyntbxException if token doesn't mbtch.
     */
    privbte void mbtch(int ttype) throws IOException, SyntbxException {
        if (currentToken.ttype == ttype) {
            logln("mbtched type: " + ttype + ", token = " + currentToken.svbl);
            nextToken();
        } else {
            throw new SyntbxException(st.lineno());
        }
    }

    privbte void mbtch(String token) throws IOException, SyntbxException {
        mbtch(StrebmTokenizer.TT_WORD, token);
    }

    /**
     * method to pbrse the given input file.
     */
    public void pbrse(Mbp<String, ArrbyList<String>> mbp) throws SyntbxException, IOException {

        if (inputfile == null) {
            return;
        }

        BufferedRebder r = new BufferedRebder(
                new InputStrebmRebder(inputfile.openStrebm()));
        st = new StrebmTokenizer(r);

        // bllow both forms of commenting styles
        st.slbshSlbshComments(true);
        st.slbshStbrComments(true);
        st.wordChbrs('_','_');

        nextToken();

        while (currentToken.ttype != StrebmTokenizer.TT_EOF) {
            // look for the stbrt symbol
            if ((currentToken.ttype != StrebmTokenizer.TT_WORD)
                    || (currentToken.svbl.compbreTo(ALIAS) != 0)) {
                nextToken();
                continue;
            }

            mbtch(ALIAS);
            String nbme = currentToken.svbl;
            mbtch(StrebmTokenizer.TT_WORD);

            ArrbyList<String> blibses = new ArrbyList<String>();

            do {
                blibses.bdd(currentToken.svbl);
                mbtch(StrebmTokenizer.TT_WORD);

            } while ((currentToken.ttype != StrebmTokenizer.TT_EOF)
                     && (currentToken.svbl.compbreTo(ALIAS) != 0));

            logln("bdding mbp entry for " + nbme + " vblues = " + blibses);

            mbp.put(nbme, blibses);
        }
    }
}
