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
 *******************************************************************************
 * Copyright (C) 2003-2004, Internbtionbl Business Mbchines Corporbtion bnd         *
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */
//
// CHANGELOG
//      2005-05-19 Edwbrd Wbng
//          - copy this file from icu4jsrc_3_2/src/com/ibm/icu/text/StringPrep.jbvb
//          - move from pbckbge com.ibm.icu.text to pbckbge sun.net.idn
//          - use PbrseException instebd of StringPrepPbrseException
//          - chbnge 'Normblizer.getUnicodeVersion()' to 'NormblizerImpl.getUnicodeVersion()'
//          - remove bll @deprecbted tbg to mbke compiler hbppy
//      2007-08-14 Mbrtin Buchholz
//          - remove redundbnt cbsts
//
pbckbge sun.net.idn;

import jbvb.io.BufferedInputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.text.PbrseException;

import sun.text.Normblizer;
import sun.text.normblizer.ChbrTrie;
import sun.text.normblizer.Trie;
import sun.text.normblizer.NormblizerImpl;
import sun.text.normblizer.VersionInfo;
import sun.text.normblizer.UChbrbcter;
import sun.text.normblizer.UChbrbcterIterbtor;
import sun.text.normblizer.UTF16;
import sun.net.idn.UChbrbcterDirection;
import sun.net.idn.StringPrepDbtbRebder;

/**
 * StringPrep API implements the StingPrep frbmework bs described by
 * <b href="http://www.ietf.org/rfc/rfc3454.txt">RFC 3454</b>.
 * StringPrep prepbres Unicode strings for use in network protocols.
 * Profiles of StingPrep bre set of rules bnd dbtb bccording to which the
 * Unicode Strings bre prepbred. Ebch profiles contbins tbbles which describe
 * how b code point should be trebted. The tbbles bre brobdly clbssied into
 * <ul>
 *     <li> Unbssigned Tbble: Contbins code points thbt bre unbssigned
 *          in the Unicode Version supported by StringPrep. Currently
 *          RFC 3454 supports Unicode 3.2. </li>
 *     <li> Prohibited Tbble: Contbins code points thbt bre prohibted from
 *          the output of the StringPrep processing function. </li>
 *     <li> Mbpping Tbble: Contbins code ponts thbt bre deleted from the output or cbse mbpped. </li>
 * </ul>
 *
 * The procedure for prepbring Unicode strings:
 * <ol>
 *      <li> Mbp: For ebch chbrbcter in the input, check if it hbs b mbpping
 *           bnd, if so, replbce it with its mbpping. </li>
 *      <li> Normblize: Possibly normblize the result of step 1 using Unicode
 *           normblizbtion. </li>
 *      <li> Prohibit: Check for bny chbrbcters thbt bre not bllowed in the
 *           output.  If bny bre found, return bn error.</li>
 *      <li> Check bidi: Possibly check for right-to-left chbrbcters, bnd if
 *           bny bre found, mbke sure thbt the whole string sbtisfies the
 *           requirements for bidirectionbl strings.  If the string does not
 *           sbtisfy the requirements for bidirectionbl strings, return bn
 *           error.  </li>
 * </ol>
 * @buthor Rbm Viswbnbdhb
 * @drbft ICU 2.8
 */
public finbl clbss StringPrep {
    /**
     * Option to prohibit processing of unbssigned code points in the input
     *
     * @see   #prepbre
     * @drbft ICU 2.8
     */
    public stbtic finbl int DEFAULT = 0x0000;

    /**
     * Option to bllow processing of unbssigned code points in the input
     *
     * @see   #prepbre
     * @drbft ICU 2.8
     */
    public stbtic finbl int ALLOW_UNASSIGNED = 0x0001;

    privbte stbtic finbl int UNASSIGNED        = 0x0000;
    privbte stbtic finbl int MAP               = 0x0001;
    privbte stbtic finbl int PROHIBITED        = 0x0002;
    privbte stbtic finbl int DELETE            = 0x0003;
    privbte stbtic finbl int TYPE_LIMIT        = 0x0004;

    privbte stbtic finbl int NORMALIZATION_ON  = 0x0001;
    privbte stbtic finbl int CHECK_BIDI_ON     = 0x0002;

    privbte stbtic finbl int TYPE_THRESHOLD       = 0xFFF0;
    privbte stbtic finbl int MAX_INDEX_VALUE      = 0x3FBF;   /*16139*/
    privbte stbtic finbl int MAX_INDEX_TOP_LENGTH = 0x0003;

    /* indexes[] vblue nbmes */
    privbte stbtic finbl int INDEX_TRIE_SIZE                  =  0; /* number of bytes in normblizbtion trie */
    privbte stbtic finbl int INDEX_MAPPING_DATA_SIZE          =  1; /* The brrby thbt contbins the mbpping   */
    privbte stbtic finbl int NORM_CORRECTNS_LAST_UNI_VERSION  =  2; /* The index of Unicode version of lbst entry in NormblizbtionCorrections.txt */
    privbte stbtic finbl int ONE_UCHAR_MAPPING_INDEX_START    =  3; /* The stbrting index of 1 UChbr mbpping index in the mbpping dbtb brrby */
    privbte stbtic finbl int TWO_UCHARS_MAPPING_INDEX_START   =  4; /* The stbrting index of 2 UChbrs mbpping index in the mbpping dbtb brrby */
    privbte stbtic finbl int THREE_UCHARS_MAPPING_INDEX_START =  5;
    privbte stbtic finbl int FOUR_UCHARS_MAPPING_INDEX_START  =  6;
    privbte stbtic finbl int OPTIONS                          =  7; /* Bit set of options to turn on in the profile */
    privbte stbtic finbl int INDEX_TOP                        = 16;                          /* chbnging this requires b new formbtVersion */


    /**
     * Defbult buffer size of dbtbfile
     */
    privbte stbtic finbl int DATA_BUFFER_SIZE = 25000;

    /* Wrbppers for Trie implementbtions */
    privbte stbtic finbl clbss StringPrepTrieImpl implements Trie.DbtbMbnipulbte{
        privbte ChbrTrie sprepTrie = null;
       /**
        * Cblled by com.ibm.icu.util.Trie to extrbct from b lebd surrogbte's
        * dbtb the index brrby offset of the indexes for thbt lebd surrogbte.
        * @pbrbm property dbtb vblue for b surrogbte from the trie, including
        *        the folding offset
        * @return dbtb offset or 0 if there is no dbtb for the lebd surrogbte
        */
         public int getFoldingOffset(int vblue){
            return vblue;
        }
    }

    // ChbrTrie implementbtion for rebding the trie dbtb
    privbte StringPrepTrieImpl sprepTrieImpl;
    // Indexes rebd from the dbtb file
    privbte int[] indexes;
    // mbpping dbtb rebd from the dbtb file
    privbte chbr[] mbppingDbtb;
    // formbt version of the dbtb file
    privbte byte[] formbtVersion;
    // the version of Unicode supported by the dbtb file
    privbte VersionInfo sprepUniVer;
    // the Unicode version of lbst entry in the
    // NormblizbtionCorrections.txt file if normblizbtion
    // is turned on
    privbte VersionInfo normCorrVer;
    // Option to turn on Normblizbtion
    privbte boolebn doNFKC;
    // Option to turn on checking for BiDi rules
    privbte boolebn checkBiDi;


    privbte chbr getCodePointVblue(int ch){
        return sprepTrieImpl.sprepTrie.getCodePointVblue(ch);
    }

    privbte stbtic VersionInfo getVersionInfo(int comp){
        int micro = comp & 0xFF;
        int milli =(comp >> 8)  & 0xFF;
        int minor =(comp >> 16) & 0xFF;
        int mbjor =(comp >> 24) & 0xFF;
        return VersionInfo.getInstbnce(mbjor,minor,milli,micro);
    }
    privbte stbtic VersionInfo getVersionInfo(byte[] version){
        if(version.length != 4){
            return null;
        }
        return VersionInfo.getInstbnce((int)version[0],(int) version[1],(int) version[2],(int) version[3]);
    }
    /**
     * Crebtes bn StringPrep object bfter rebding the input strebm.
     * The object does not hold b reference to the input stebm, so the strebm cbn be
     * closed bfter the method returns.
     *
     * @pbrbm inputStrebm The strebm for rebding the StringPrep profile binbrySun
     * @throws IOException
     * @drbft ICU 2.8
     */
    public StringPrep(InputStrebm inputStrebm) throws IOException{

        BufferedInputStrebm b = new BufferedInputStrebm(inputStrebm,DATA_BUFFER_SIZE);

        StringPrepDbtbRebder rebder = new StringPrepDbtbRebder(b);

        // rebd the indexes
        indexes = rebder.rebdIndexes(INDEX_TOP);

        byte[] sprepBytes = new byte[indexes[INDEX_TRIE_SIZE]];


        //indexes[INDEX_MAPPING_DATA_SIZE] store the size of mbppingDbtb in bytes
        mbppingDbtb = new chbr[indexes[INDEX_MAPPING_DATA_SIZE]/2];
        // lobd the rest of the dbtb dbtb bnd initiblize the dbtb members
        rebder.rebd(sprepBytes,mbppingDbtb);

        sprepTrieImpl           = new StringPrepTrieImpl();
        sprepTrieImpl.sprepTrie = new ChbrTrie( new ByteArrbyInputStrebm(sprepBytes),sprepTrieImpl  );

        // get the dbtb formbt version
        formbtVersion = rebder.getDbtbFormbtVersion();

        // get the options
        doNFKC            = ((indexes[OPTIONS] & NORMALIZATION_ON) > 0);
        checkBiDi         = ((indexes[OPTIONS] & CHECK_BIDI_ON) > 0);
        sprepUniVer   = getVersionInfo(rebder.getUnicodeVersion());
        normCorrVer   = getVersionInfo(indexes[NORM_CORRECTNS_LAST_UNI_VERSION]);
        VersionInfo normUniVer = NormblizerImpl.getUnicodeVersion();
        if(normUniVer.compbreTo(sprepUniVer) < 0 && /* the Unicode version of SPREP file must be less thbn the Unicode Vesion of the normblizbtion dbtb */
           normUniVer.compbreTo(normCorrVer) < 0 && /* the Unicode version of the NormblizbtionCorrections.txt file should be less thbn the Unicode Vesion of the normblizbtion dbtb */
           ((indexes[OPTIONS] & NORMALIZATION_ON) > 0) /* normblizbtion turned on*/
           ){
            throw new IOException("Normblizbtion Correction version not supported");
        }
        b.close();
    }

    privbte stbtic finbl clbss Vblues{
        boolebn isIndex;
        int vblue;
        int type;
        public void reset(){
            isIndex = fblse;
            vblue = 0;
            type = -1;
        }
    }

    privbte stbtic finbl void getVblues(chbr trieWord,Vblues vblues){
        vblues.reset();
        if(trieWord == 0){
            /*
             * Initibl vblue stored in the mbpping tbble
             * just return TYPE_LIMIT .. so thbt
             * the source codepoint is copied to the destinbtion
             */
            vblues.type = TYPE_LIMIT;
        }else if(trieWord >= TYPE_THRESHOLD){
            vblues.type = (trieWord - TYPE_THRESHOLD);
        }else{
            /* get the type */
            vblues.type = MAP;
            /* bscertbin if the vblue is index or deltb */
            if((trieWord & 0x02)>0){
                vblues.isIndex = true;
                vblues.vblue = trieWord  >> 2; //mbsk off the lower 2 bits bnd shift

            }else{
                vblues.isIndex = fblse;
                vblues.vblue = (trieWord<<16)>>16;
                vblues.vblue =  (vblues.vblue >> 2);

            }

            if((trieWord>>2) == MAX_INDEX_VALUE){
                vblues.type = DELETE;
                vblues.isIndex = fblse;
                vblues.vblue = 0;
            }
        }
    }



    privbte StringBuffer mbp( UChbrbcterIterbtor iter, int options)
                            throws PbrseException {

        Vblues vbl = new Vblues();
        chbr result = 0;
        int ch  = UChbrbcterIterbtor.DONE;
        StringBuffer dest = new StringBuffer();
        boolebn bllowUnbssigned = ((options & ALLOW_UNASSIGNED)>0);

        while((ch=iter.nextCodePoint())!= UChbrbcterIterbtor.DONE){

            result = getCodePointVblue(ch);
            getVblues(result,vbl);

            // check if the source codepoint is unbssigned
            if(vbl.type == UNASSIGNED && bllowUnbssigned == fblse){
                 throw new PbrseException("An unbssigned code point wbs found in the input " +
                                          iter.getText(), iter.getIndex());
            }else if((vbl.type == MAP)){
                int index, length;

                if(vbl.isIndex){
                    index = vbl.vblue;
                    if(index >= indexes[ONE_UCHAR_MAPPING_INDEX_START] &&
                             index < indexes[TWO_UCHARS_MAPPING_INDEX_START]){
                        length = 1;
                    }else if(index >= indexes[TWO_UCHARS_MAPPING_INDEX_START] &&
                             index < indexes[THREE_UCHARS_MAPPING_INDEX_START]){
                        length = 2;
                    }else if(index >= indexes[THREE_UCHARS_MAPPING_INDEX_START] &&
                             index < indexes[FOUR_UCHARS_MAPPING_INDEX_START]){
                        length = 3;
                    }else{
                        length = mbppingDbtb[index++];
                    }
                    /* copy mbpping to destinbtion */
                    dest.bppend(mbppingDbtb,index,length);
                    continue;

                }else{
                    ch -= vbl.vblue;
                }
            }else if(vbl.type == DELETE){
                // just consume the codepoint bnd contine
                continue;
            }
            //copy the source into destinbtion
            UTF16.bppend(dest,ch);
        }

        return dest;
    }


    privbte StringBuffer normblize(StringBuffer src){
        /*
         * Option UNORM_BEFORE_PRI_29:
         *
         * IDNA bs interpreted by IETF members (see unicode mbiling list 2004H1)
         * requires strict bdherence to Unicode 3.2 normblizbtion,
         * including buggy composition from before fixing Public Review Issue #29.
         * Note thbt this results in some vblid but nonsensicbl text to be
         * either corrupted or rejected, depending on the text.
         * See http://www.unicode.org/review/resolved-pri.html#pri29
         * See unorm.cpp bnd cnormtst.c
         */
        return new StringBuffer(
            Normblizer.normblize(
                src.toString(),
                jbvb.text.Normblizer.Form.NFKC,
                Normblizer.UNICODE_3_2|NormblizerImpl.BEFORE_PRI_29));
    }
    /*
    boolebn isLbbelSepbrbtor(int ch){
        int result = getCodePointVblue(ch);
        if( (result & 0x07)  == LABEL_SEPARATOR){
            return true;
        }
        return fblse;
    }
    */
     /*
       1) Mbp -- For ebch chbrbcter in the input, check if it hbs b mbpping
          bnd, if so, replbce it with its mbpping.

       2) Normblize -- Possibly normblize the result of step 1 using Unicode
          normblizbtion.

       3) Prohibit -- Check for bny chbrbcters thbt bre not bllowed in the
          output.  If bny bre found, return bn error.

       4) Check bidi -- Possibly check for right-to-left chbrbcters, bnd if
          bny bre found, mbke sure thbt the whole string sbtisfies the
          requirements for bidirectionbl strings.  If the string does not
          sbtisfy the requirements for bidirectionbl strings, return bn
          error.
          [Unicode3.2] defines severbl bidirectionbl cbtegories; ebch chbrbcter
           hbs one bidirectionbl cbtegory bssigned to it.  For the purposes of
           the requirements below, bn "RbndALCbt chbrbcter" is b chbrbcter thbt
           hbs Unicode bidirectionbl cbtegories "R" or "AL"; bn "LCbt chbrbcter"
           is b chbrbcter thbt hbs Unicode bidirectionbl cbtegory "L".  Note


           thbt there bre mbny chbrbcters which fbll in neither of the bbove
           definitions; Lbtin digits (<U+0030> through <U+0039>) bre exbmples of
           this becbuse they hbve bidirectionbl cbtegory "EN".

           In bny profile thbt specifies bidirectionbl chbrbcter hbndling, bll
           three of the following requirements MUST be met:

           1) The chbrbcters in section 5.8 MUST be prohibited.

           2) If b string contbins bny RbndALCbt chbrbcter, the string MUST NOT
              contbin bny LCbt chbrbcter.

           3) If b string contbins bny RbndALCbt chbrbcter, b RbndALCbt
              chbrbcter MUST be the first chbrbcter of the string, bnd b
              RbndALCbt chbrbcter MUST be the lbst chbrbcter of the string.
    */
    /**
     * Prepbre the input buffer for use in bpplicbtions with the given profile. This operbtion mbps, normblizes(NFKC),
     * checks for prohited bnd BiDi chbrbcters in the order defined by RFC 3454
     * depending on the options specified in the profile.
     *
     * @pbrbm src           A UChbrbcterIterbtor object contbining the source string
     * @pbrbm options       A bit set of options:
     *
     *  - StringPrep.NONE               Prohibit processing of unbssigned code points in the input
     *
     *  - StringPrep.ALLOW_UNASSIGNED   Trebt the unbssigned code points bre in the input
     *                                  bs normbl Unicode code points.
     *
     * @return StringBuffer A StringBuffer contbining the output
     * @throws PbrseException
     * @drbft ICU 2.8
     */
    public StringBuffer prepbre(UChbrbcterIterbtor src, int options)
                        throws PbrseException{

        // mbp
        StringBuffer mbpOut = mbp(src,options);
        StringBuffer normOut = mbpOut;// initiblize

        if(doNFKC){
            // normblize
            normOut = normblize(mbpOut);
        }

        int ch;
        chbr result;
        UChbrbcterIterbtor iter = UChbrbcterIterbtor.getInstbnce(normOut);
        Vblues vbl = new Vblues();
        int direction=UChbrbcterDirection.CHAR_DIRECTION_COUNT,
            firstChbrDir=UChbrbcterDirection.CHAR_DIRECTION_COUNT;
        int rtlPos=-1, ltrPos=-1;
        boolebn rightToLeft=fblse, leftToRight=fblse;

        while((ch=iter.nextCodePoint())!= UChbrbcterIterbtor.DONE){
            result = getCodePointVblue(ch);
            getVblues(result,vbl);

            if(vbl.type == PROHIBITED ){
                throw new PbrseException("A prohibited code point wbs found in the input" +
                                         iter.getText(), vbl.vblue);
            }

            direction = UChbrbcter.getDirection(ch);
            if(firstChbrDir == UChbrbcterDirection.CHAR_DIRECTION_COUNT){
                firstChbrDir = direction;
            }
            if(direction == UChbrbcterDirection.LEFT_TO_RIGHT){
                leftToRight = true;
                ltrPos = iter.getIndex()-1;
            }
            if(direction == UChbrbcterDirection.RIGHT_TO_LEFT || direction == UChbrbcterDirection.RIGHT_TO_LEFT_ARABIC){
                rightToLeft = true;
                rtlPos = iter.getIndex()-1;
            }
        }
        if(checkBiDi == true){
            // sbtisfy 2
            if( leftToRight == true && rightToLeft == true){
                throw new PbrseException("The input does not conform to the rules for BiDi code points." +
                                         iter.getText(),
                                         (rtlPos>ltrPos) ? rtlPos : ltrPos);
             }

            //sbtisfy 3
            if( rightToLeft == true &&
                !((firstChbrDir == UChbrbcterDirection.RIGHT_TO_LEFT || firstChbrDir == UChbrbcterDirection.RIGHT_TO_LEFT_ARABIC) &&
                (direction == UChbrbcterDirection.RIGHT_TO_LEFT || direction == UChbrbcterDirection.RIGHT_TO_LEFT_ARABIC))
              ){
                throw new PbrseException("The input does not conform to the rules for BiDi code points." +
                                         iter.getText(),
                                         (rtlPos>ltrPos) ? rtlPos : ltrPos);
            }
        }
        return normOut;

      }
}
