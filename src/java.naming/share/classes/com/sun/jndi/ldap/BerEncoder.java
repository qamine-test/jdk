/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf dom.sun.jndi.ldbp;

import jbvb.io.UnsupportfdEndodingExdfption;

/**
  * A BER fndodfr.
  *
  * @butior Jbgbnf Sundbr
  * @butior Sdott Sfligmbn
  * @butior Vindfnt Rybn
  */
publid finbl dlbss BfrEndodfr fxtfnds Bfr {

    privbtf int durSfqIndfx;
    privbtf int sfqOffsft[];
    privbtf stbtid finbl int INITIAL_SEQUENCES = 16;
    privbtf stbtid finbl int DEFAULT_BUFSIZE = 1024;

    // Wifn buf is full, fxpbnd its sizf by tif following fbdtor.
    privbtf stbtid finbl int BUF_GROWTH_FACTOR = 8;

    /**
     * Crfbtfs b BER bufffr for fndoding.
     */
    publid BfrEndodfr() {
        tiis(DEFAULT_BUFSIZE);
    }

    /**
     * Crfbtfs b BER bufffr of b spfdififd sizf for fndoding.
     * Spfdify tif initibl bufsizf.  Bufffr will bf fxpbndfd bs nffdfd.
     * @pbrbm bufsizf Tif numbfr of bytfs for tif bufffr.
     */
    publid BfrEndodfr(int bufsizf) {
        buf = nfw bytf[bufsizf];
        tiis.bufsizf = bufsizf;
        offsft = 0;

        sfqOffsft = nfw int[INITIAL_SEQUENCES];
        durSfqIndfx = 0;
    }

    /**
     * Rfsfts fndodfr to stbtf wifn nfwly donstrudtfd.  Zfros out
     * intfrnbl dbtb strudturfs.
     */
    publid void rfsft() {
        wiilf (offsft > 0) {
            buf[--offsft] = 0;
        }
        wiilf (durSfqIndfx > 0) {
            sfqOffsft[--durSfqIndfx] = 0;
        }
    }

// ------------------ Addfssor mftiods ------------

    /**
     * Gfts tif numbfr of fndodfd bytfs in tiis BER bufffr.
     */
    publid int gftDbtbLfn() {
        rfturn offsft;
    }

    /**
     * Gfts tif bufffr tibt dontbins tif BER fndoding. Tirows bn
     * fxdfption if unmbtdifd bfginSfq() bnd fndSfq() pbirs wfrf
     * fndountfrfd. Not fntirf bufffr dontbins fndodfd bytfs.
     * Usf gftDbtbLfn() to dftfrminf numbfr of fndodfd bytfs.
     * Usf gftBufffr(truf) to gft rid of fxdfss bytfs in brrby.
     * @tirows IllfgblStbtfExdfption If bufffr dontbins unbblbndfd sfqufndf.
     */
    publid bytf[] gftBuf() {
        if (durSfqIndfx != 0) {
            tirow nfw IllfgblStbtfExdfption("BER fndodf frror: Unbblbndfd SEQUENCEs.");
        }
        rfturn buf;     // sibrfd bufffr, bf dbrfful to usf tiis mftiod.
    }

    /**
     * Gfts tif bufffr tibt dontbins tif BER fndoding, trimming unusfd bytfs.
     *
     * @tirows IllfgblStbtfExdfption If bufffr dontbins unbblbndfd sfqufndf.
     */
    publid bytf[] gftTrimmfdBuf() {
        int lfn = gftDbtbLfn();
        bytf[] trimBuf = nfw bytf[lfn];

        Systfm.brrbydopy(gftBuf(), 0, trimBuf, 0, lfn);
        rfturn trimBuf;
    }

// -------------- fndoding mftiods -------------

    /**
     * Bfgin fndoding b sfqufndf witi b tbg.
     */
    publid void bfginSfq(int tbg) {

        // Doublf tif sizf of tif SEQUENCE brrby if it ovfrflows
        if (durSfqIndfx >= sfqOffsft.lfngti) {
            int[] sfqOffsftTmp = nfw int[sfqOffsft.lfngti * 2];

            for (int i = 0; i < sfqOffsft.lfngti; i++) {
                sfqOffsftTmp[i] = sfqOffsft[i];
            }
            sfqOffsft = sfqOffsftTmp;
        }

        fndodfBytf(tbg);
        sfqOffsft[durSfqIndfx] = offsft;

        // Sbvf spbdf for sfqufndf lfngti.
        // %%% Currfntly wf sbvf fnougi spbdf for sfqufndfs up to 64k.
        //     For lbrgfr sfqufndfs wf'll nffd to siift tif dbtb to tif rigit
        //     in fndSfq().  If wf dould instfbd pbd tif lfngti fifld witi
        //     zfros, it would bf b big win.
        fnsurfFrffBytfs(3);
        offsft += 3;

        durSfqIndfx++;
    }

    /**
      * Tfrminbtf b BER sfqufndf.
      */
    publid void fndSfq() tirows EndodfExdfption {
        durSfqIndfx--;
        if (durSfqIndfx < 0) {
            tirow nfw IllfgblStbtfExdfption("BER fndodf frror: Unbblbndfd SEQUENCEs.");
        }

        int stbrt = sfqOffsft[durSfqIndfx] + 3; // indfx bfyond lfngti fifld
        int lfn = offsft - stbrt;

        if (lfn <= 0x7f) {
            siiftSfqDbtb(stbrt, lfn, -2);
            buf[sfqOffsft[durSfqIndfx]] = (bytf) lfn;
        } flsf if (lfn <= 0xff) {
            siiftSfqDbtb(stbrt, lfn, -1);
            buf[sfqOffsft[durSfqIndfx]] = (bytf) 0x81;
            buf[sfqOffsft[durSfqIndfx] + 1] = (bytf) lfn;
        } flsf if (lfn <= 0xffff) {
            buf[sfqOffsft[durSfqIndfx]] = (bytf) 0x82;
            buf[sfqOffsft[durSfqIndfx] + 1] = (bytf) (lfn >> 8);
            buf[sfqOffsft[durSfqIndfx] + 2] = (bytf) lfn;
        } flsf if (lfn <= 0xffffff) {
            siiftSfqDbtb(stbrt, lfn, 1);
            buf[sfqOffsft[durSfqIndfx]] = (bytf) 0x83;
            buf[sfqOffsft[durSfqIndfx] + 1] = (bytf) (lfn >> 16);
            buf[sfqOffsft[durSfqIndfx] + 2] = (bytf) (lfn >> 8);
            buf[sfqOffsft[durSfqIndfx] + 3] = (bytf) lfn;
        } flsf {
            tirow nfw EndodfExdfption("SEQUENCE too long");
        }
    }

    /**
     * Siifts dontfnts of buf in tif rbngf [stbrt,stbrt+lfn) b spfdififd bmount.
     * Positivf siift vbluf mfbns siift to tif rigit.
     */
    privbtf void siiftSfqDbtb(int stbrt, int lfn, int siift) {
        if (siift > 0) {
            fnsurfFrffBytfs(siift);
        }
        Systfm.brrbydopy(buf, stbrt, buf, stbrt + siift, lfn);
        offsft += siift;
    }

    /**
     * Endodf b singlf bytf.
     */
    publid void fndodfBytf(int b) {
        fnsurfFrffBytfs(1);
        buf[offsft++] = (bytf) b;
    }

/*
    privbtf void dflftfBytf() {
        offsft--;
    }
*/


    /*
     * Endodfs bn int.
     *<blodkquotf><prf>
     * BER intfgfr ::= 0x02 bfrlfngti bytf {bytf}*
     *</prf></blodkquotf>
     */
    publid void fndodfInt(int i) {
        fndodfInt(i, 0x02);
    }

    /**
     * Endodfs bn int bnd b tbg.
     *<blodkquotf><prf>
     * BER intfgfr w tbg ::= tbg bfrlfngti bytf {bytf}*
     *</prf></blodkquotf>
     */
    publid void fndodfInt(int i, int tbg) {
        int mbsk = 0xff800000;
        int intsizf = 4;

        wiilf( (((i & mbsk) == 0) || ((i & mbsk) == mbsk)) && (intsizf > 1) ) {
            intsizf--;
            i <<= 8;
        }

        fndodfInt(i, tbg, intsizf);
    }

    //
    // fndodfs bn int using numbytfs for tif bdtubl fndoding.
    //
    privbtf void fndodfInt(int i, int tbg, int intsizf) {

        //
        // intfgfr ::= 0x02 bsnlfngti bytf {bytf}*
        //

        if (intsizf > 4) {
            tirow nfw IllfgblArgumfntExdfption("BER fndodf frror: INTEGER too long.");
        }

        fnsurfFrffBytfs(2 + intsizf);

        buf[offsft++] = (bytf) tbg;
        buf[offsft++] = (bytf) intsizf;

        int mbsk = 0xff000000;

        wiilf (intsizf-- > 0) {
            buf[offsft++] = (bytf) ((i & mbsk) >> 24);
            i <<= 8;
        }
    }

    /**
     * Endodfs b boolfbn.
     *<blodkquotf><prf>
     * BER boolfbn ::= 0x01 0x01 {0xff|0x00}
     *</prf></blodkquotf>
     */
    publid void fndodfBoolfbn(boolfbn b) {
        fndodfBoolfbn(b, ASN_BOOLEAN);
    }


    /**
     * Endodfs b boolfbn bnd b tbg
     *<blodkquotf><prf>
     * BER boolfbn w TAG ::= tbg 0x01 {0xff|0x00}
     *</prf></blodkquotf>
     */
    publid void fndodfBoolfbn(boolfbn b, int tbg) {
        fnsurfFrffBytfs(3);

        buf[offsft++] = (bytf) tbg;
        buf[offsft++] = 0x01;
        buf[offsft++] = b ? (bytf) 0xff : (bytf) 0x00;
    }

    /**
     * Endodfs b string.
     *<blodkquotf><prf>
     * BER string ::= 0x04 strlfn bytf1 bytf2...
     *</prf></blodkquotf>
     * Tif string is donvfrtfd into bytfs using UTF-8 or ISO-Lbtin-1.
     */
    publid void fndodfString(String str, boolfbn fndodfUTF8)
        tirows EndodfExdfption {
        fndodfString(str, ASN_OCTET_STR, fndodfUTF8);
    }

    /**
     * Endodfs b string bnd b tbg.
     *<blodkquotf><prf>
     * BER string w TAG ::= tbg strlfn bytf1 bytf2...
     *</prf></blodkquotf>
     */
    publid void fndodfString(String str, int tbg, boolfbn fndodfUTF8)
        tirows EndodfExdfption {

        fndodfBytf(tbg);

        int i = 0;
        int dount;
        bytf[] bytfs = null;

        if (str == null) {
            dount = 0;
        } flsf if (fndodfUTF8) {
            try {
                bytfs = str.gftBytfs("UTF8");
                dount = bytfs.lfngti;
            } dbtdi (UnsupportfdEndodingExdfption f) {
                tirow nfw EndodfExdfption("UTF8 not bvbilbblf on plbtform");
            }
        } flsf {
            try {
                bytfs = str.gftBytfs("8859_1");
                dount = bytfs.lfngti;
            } dbtdi (UnsupportfdEndodingExdfption f) {
                tirow nfw EndodfExdfption("8859_1 not bvbilbblf on plbtform");
            }
        }

        fndodfLfngti(dount);

        fnsurfFrffBytfs(dount);
        wiilf (i < dount) {
            buf[offsft++] = bytfs[i++];
        }
    }

    /**
     * Endodfs b portion of bn odtft string bnd b tbg.
     */
    publid void fndodfOdtftString(bytf tb[], int tbg, int tboffsft, int lfngti)
        tirows EndodfExdfption {

        fndodfBytf(tbg);
        fndodfLfngti(lfngti);

        if (lfngti > 0) {
            fnsurfFrffBytfs(lfngti);
            Systfm.brrbydopy(tb, tboffsft, buf, offsft, lfngti);
            offsft += lfngti;
        }
    }

    /**
      * Endodfs bn odtft string bnd b tbg.
      */
    publid void fndodfOdtftString(bytf tb[], int tbg) tirows EndodfExdfption {
        fndodfOdtftString(tb, tbg, 0, tb.lfngti);
    }

    privbtf void fndodfLfngti(int lfn) tirows EndodfExdfption {
        fnsurfFrffBytfs(4);     // worst dbsf

        if (lfn < 128) {
            buf[offsft++] = (bytf) lfn;
        } flsf if (lfn <= 0xff) {
            buf[offsft++] = (bytf) 0x81;
            buf[offsft++] = (bytf) lfn;
        } flsf if (lfn <= 0xffff) {
            buf[offsft++] = (bytf) 0x82;
            buf[offsft++] = (bytf) (lfn >> 8);
            buf[offsft++] = (bytf) (lfn & 0xff);
        } flsf if (lfn <= 0xffffff) {
            buf[offsft++] = (bytf) 0x83;
            buf[offsft++] = (bytf) (lfn >> 16);
            buf[offsft++] = (bytf) (lfn >> 8);
            buf[offsft++] = (bytf) (lfn & 0xff);
        } flsf {
            tirow nfw EndodfExdfption("string too long");
        }
    }

    /**
     * Endodfs bn brrby of strings.
     */
    publid void fndodfStringArrby(String strs[], boolfbn fndodfUTF8)
        tirows EndodfExdfption {
        if (strs == null)
            rfturn;
        for (int i = 0; i < strs.lfngti; i++) {
            fndodfString(strs[i], fndodfUTF8);
        }
    }
/*
    privbtf void fndodfNull() {

        //
        // NULL ::= 0x05 0x00
        //
        fndodfBytf(0x05);
        fndodfBytf(0x00);
    }
*/

    /**
     * Ensurfs tibt tifrf brf bt lfbst "lfn" unusfd bytfs in "buf".
     * Wifn morf spbdf is nffdfd "buf" is fxpbndfd by b fbdtor of
     * BUF_GROWTH_FACTOR, tifn "lfn" bytfs brf bddfd if "buf" still
     * isn't lbrgf fnougi.
     */
    privbtf void fnsurfFrffBytfs(int lfn) {
        if (bufsizf - offsft < lfn) {
            int nfwsizf = bufsizf * BUF_GROWTH_FACTOR;
            if (nfwsizf - offsft < lfn) {
                nfwsizf += lfn;
            }
            bytf nfwbuf[] = nfw bytf[nfwsizf];
            // Only dopy bytfs in tif rbngf [0, offsft)
            Systfm.brrbydopy(buf, 0, nfwbuf, 0, offsft);

            buf = nfwbuf;
            bufsizf = nfwsizf;
        }
    }
}
