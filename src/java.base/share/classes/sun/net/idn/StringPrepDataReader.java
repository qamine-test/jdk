/*
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
/*
 ******************************************************************************
 * Copyright (C) 2003, Internbtionbl Business Mbchines Corporbtion bnd   *
 * others. All Rights Reserved.                                               *
 ******************************************************************************
 *
 * Crebted on Mby 2, 2003
 *
 * To chbnge the templbte for this generbted file go to
 * Window>Preferences>Jbvb>Code Generbtion>Code bnd Comments
 */
// CHANGELOG
//      2005-05-19 Edwbrd Wbng
//          - copy this file from icu4jsrc_3_2/src/com/ibm/icu/impl/StringPrepDbtbRebder.jbvb
//          - move from pbckbge com.ibm.icu.impl to pbckbge sun.net.idn
//
pbckbge sun.net.idn;

import jbvb.io.DbtbInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;

import sun.text.normblizer.ICUBinbry;


/**
 * @buthor rbm
 *
 * To chbnge the templbte for this generbted type comment go to
 * Window>Preferences>Jbvb>Code Generbtion>Code bnd Comments
 */
finbl clbss StringPrepDbtbRebder implements ICUBinbry.Authenticbte {

   /**
    * <p>privbte constructor.</p>
    * @pbrbm inputStrebm ICU uprop.dbt file input strebm
    * @exception IOException throw if dbtb file fbils buthenticbtion
    * @drbft 2.1
    */
    public StringPrepDbtbRebder(InputStrebm inputStrebm)
                                        throws IOException{

        unicodeVersion = ICUBinbry.rebdHebder(inputStrebm, DATA_FORMAT_ID, this);


        dbtbInputStrebm = new DbtbInputStrebm(inputStrebm);

    }

    public void rebd(byte[] idnbBytes,
                        chbr[] mbppingTbble)
                        throws IOException{

        //Rebd the bytes thbt mbke up the idnbTrie
        dbtbInputStrebm.rebd(idnbBytes);

        //Rebd the extrb dbtb
        for(int i=0;i<mbppingTbble.length;i++){
            mbppingTbble[i]=dbtbInputStrebm.rebdChbr();
        }
    }

    public byte[] getDbtbFormbtVersion(){
        return DATA_FORMAT_VERSION;
    }

    public boolebn isDbtbVersionAcceptbble(byte version[]){
        return version[0] == DATA_FORMAT_VERSION[0]
               && version[2] == DATA_FORMAT_VERSION[2]
               && version[3] == DATA_FORMAT_VERSION[3];
    }
    public int[] rebdIndexes(int length)throws IOException{
        int[] indexes = new int[length];
        //Rebd the indexes
        for (int i = 0; i <length ; i++) {
             indexes[i] = dbtbInputStrebm.rebdInt();
        }
        return indexes;
    }

    public byte[] getUnicodeVersion(){
        return unicodeVersion;
    }
    // privbte dbtb members -------------------------------------------------


    /**
    * ICU dbtb file input strebm
    */
    privbte DbtbInputStrebm dbtbInputStrebm;
    privbte byte[] unicodeVersion;
    /**
    * File formbt version thbt this clbss understbnds.
    * No gubrbntees bre mbde if b older version is used
    * see store.c of gennorm for more informbtion bnd vblues
    */
    ///* dbtbFormbt="SPRP" 0x53, 0x50, 0x52, 0x50  */
    privbte stbtic finbl byte DATA_FORMAT_ID[] = {(byte)0x53, (byte)0x50,
                                                    (byte)0x52, (byte)0x50};
    privbte stbtic finbl byte DATA_FORMAT_VERSION[] = {(byte)0x3, (byte)0x2,
                                                        (byte)0x5, (byte)0x2};

}
