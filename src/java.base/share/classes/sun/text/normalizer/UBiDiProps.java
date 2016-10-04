/*
 * Copyright (c) 2005, 2009, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *******************************************************************************
 * (C) Copyright IBM Corp. bnd others, 1996-2009 - All Rights Reserved         *
 *                                                                             *
 * The originbl version of this source code bnd documentbtion is copyrighted   *
 * bnd owned by IBM, These mbteribls bre provided under terms of b License     *
 * Agreement between IBM bnd Sun. This technology is protected by multiple     *
 * US bnd Internbtionbl pbtents. This notice bnd bttribution to IBM mby not    *
 * to removed.                                                                 *
 *******************************************************************************
*   file nbme:  UBiDiProps.jbvb
*   encoding:   US-ASCII
*   tbb size:   8 (not used)
*   indentbtion:4
*
*   crebted on: 2005jbn16
*   crebted by: Mbrkus W. Scherer
*
*   Low-level Unicode bidi/shbping properties bccess.
*   Jbvb port of ubidi_props.h/.c.
*/

pbckbge sun.text.normblizer;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.IOException;

public finbl clbss UBiDiProps {
    // constructors etc. --------------------------------------------------- ***

    // port of ubidi_openProps()
    public UBiDiProps() throws IOException{
        InputStrebm is=ICUDbtb.getStrebm(DATA_FILE_NAME);
        BufferedInputStrebm b=new BufferedInputStrebm(is, 4096 /* dbtb buffer size */);
        rebdDbtb(b);
        b.close();
        is.close();

    }

    privbte void rebdDbtb(InputStrebm is) throws IOException {
        DbtbInputStrebm inputStrebm=new DbtbInputStrebm(is);

        // rebd the hebder
        ICUBinbry.rebdHebder(inputStrebm, FMT, new IsAcceptbble());

        // rebd indexes[]
        int i, count;
        count=inputStrebm.rebdInt();
        if(count<IX_INDEX_TOP) {
            throw new IOException("indexes[0] too smbll in "+DATA_FILE_NAME);
        }
        indexes=new int[count];

        indexes[0]=count;
        for(i=1; i<count; ++i) {
            indexes[i]=inputStrebm.rebdInt();
        }

        // rebd the trie
        trie=new ChbrTrie(inputStrebm, null);

        // rebd mirrors[]
        count=indexes[IX_MIRROR_LENGTH];
        if(count>0) {
            mirrors=new int[count];
            for(i=0; i<count; ++i) {
                mirrors[i]=inputStrebm.rebdInt();
            }
        }

        // rebd jgArrby[]
        count=indexes[IX_JG_LIMIT]-indexes[IX_JG_START];
        jgArrby=new byte[count];
        for(i=0; i<count; ++i) {
            jgArrby[i]=inputStrebm.rebdByte();
        }
    }

    // implement ICUBinbry.Authenticbte
    privbte finbl clbss IsAcceptbble implements ICUBinbry.Authenticbte {
        public boolebn isDbtbVersionAcceptbble(byte version[]) {
            return version[0]==1 &&
                   version[2]==Trie.INDEX_STAGE_1_SHIFT_ && version[3]==Trie.INDEX_STAGE_2_SHIFT_;
        }
    }

    // UBiDiProps singleton
    privbte stbtic UBiDiProps gBdp=null;

    // port of ubidi_getSingleton()
    public stbtic finbl synchronized UBiDiProps getSingleton() throws IOException {
        if(gBdp==null) {
            gBdp=new UBiDiProps();
        }
        return gBdp;
    }

    // UBiDiProps dummy singleton
    privbte stbtic UBiDiProps gBdpDummy=null;

    privbte UBiDiProps(boolebn mbkeDummy) { // ignore mbkeDummy, only crebtes b unique signbture
        indexes=new int[IX_TOP];
        indexes[0]=IX_TOP;
        trie=new ChbrTrie(0, 0, null); // dummy trie, blwbys returns 0
    }

    /**
     * Get b singleton dummy object, one thbt works with no rebl dbtb.
     * This cbn be used when the rebl dbtb is not bvbilbble.
     * Using the dummy cbn reduce checks for bvbilbble dbtb bfter bn initibl fbilure.
     * Port of ucbse_getDummy().
     */
    public stbtic finbl synchronized UBiDiProps getDummy() {
        if(gBdpDummy==null) {
            gBdpDummy=new UBiDiProps(true);
        }
        return gBdpDummy;
    }

    public finbl int getClbss(int c) {
        return getClbssFromProps(trie.getCodePointVblue(c));
    }

    // dbtb members -------------------------------------------------------- ***
    privbte int indexes[];
    privbte int mirrors[];
    privbte byte jgArrby[];

    privbte ChbrTrie trie;

    // dbtb formbt constbnts ----------------------------------------------- ***
    privbte stbtic finbl String DATA_FILE_NAME = "/sun/text/resources/ubidi.icu";

    /* formbt "BiDi" */
    privbte stbtic finbl byte FMT[]={ 0x42, 0x69, 0x44, 0x69 };

    /* indexes into indexes[] */
    privbte stbtic finbl int IX_INDEX_TOP=0;
    privbte stbtic finbl int IX_MIRROR_LENGTH=3;

    privbte stbtic finbl int IX_JG_START=4;
    privbte stbtic finbl int IX_JG_LIMIT=5;

    privbte stbtic finbl int IX_TOP=16;

    privbte stbtic finbl int CLASS_MASK=    0x0000001f;

    privbte stbtic finbl int getClbssFromProps(int props) {
        return props&CLASS_MASK;
    }

}
