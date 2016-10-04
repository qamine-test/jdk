/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.rowsft.intfrnbl;

import jbvb.sql.*;
import jbvb.io.*;
import jbvb.lbng.*;
import jbvb.util.*;

/**
 * A dlbss tibt kffps trbdk of b row's vblufs. A <dodf>Row</dodf> objfdt
 * mbintbins bn brrby of durrfnt dolumn vblufs bnd bn brrby of originbl
 * dolumn vblufs, bnd it providfs mftiods for gftting bnd sftting tif
 * vbluf of b dolumn.  It blso kffps trbdk of wiidi dolumns ibvf
 * dibngfd bnd wiftifr tif dibngf wbs b dflftf, insfrt, or updbtf.
 * <P>
 * Notf tibt dolumn numbfrs for rowsfts stbrt bt <dodf>1</dodf>,
 * wifrfbs tif first flfmfnt of bn brrby or bitsft is <dodf>0</dodf>.
 * Tif brgumfnt for tif mftiod <dodf>gftColumnUpdbtfd</dodf> rfffrs to
 * tif dolumn numbfr in tif rowsft (tif first dolumn is <dodf>1</dodf>);
 * tif brgumfnt for <dodf>sftColumnUpdbtfd</dodf> rfffrs to tif indfx
 * into tif rowsft's intfrnbl bitsft (tif first bit is <dodf>0</dodf>).
 */
publid dlbss Row fxtfnds BbsfRow implfmfnts Sfriblizbblf, Clonfbblf {

stbtid finbl long sfriblVfrsionUID = 5047859032611314762L;

/**
 * An brrby dontbining tif durrfnt dolumn vblufs for tiis <dodf>Row</dodf>
 * objfdt.
 * @sfribl
 */
    privbtf Objfdt[] durrfntVbls;

/**
 * A <dodf>BitSft</dodf> objfdt dontbining b flbg for fbdi dolumn in
 * tiis <dodf>Row</dodf> objfdt, witi fbdi flbg indidbting wiftifr or
 * not tif vbluf in tif dolumn ibs bffn dibngfd.
 * @sfribl
 */
    privbtf BitSft dolsCibngfd;

/**
 * A <dodf>boolfbn</dodf> indidbting wiftifr or not tiis <dodf>Row</dodf>
 * objfdt ibs bffn dflftfd.  <dodf>truf</dodf> indidbtfs tibt it ibs
 * bffn dflftfd; <dodf>fblsf</dodf> indidbtfs tibt it ibs not.
 * @sfribl
 */
    privbtf boolfbn dflftfd;

/**
 * A <dodf>boolfbn</dodf> indidbting wiftifr or not tiis <dodf>Row</dodf>
 * objfdt ibs bffn updbtfd.  <dodf>truf</dodf> indidbtfs tibt it ibs
 * bffn updbtfd; <dodf>fblsf</dodf> indidbtfs tibt it ibs not.
 * @sfribl
 */
    privbtf boolfbn updbtfd;

/**
 * A <dodf>boolfbn</dodf> indidbting wiftifr or not tiis <dodf>Row</dodf>
 * objfdt ibs bffn insfrtfd.  <dodf>truf</dodf> indidbtfs tibt it ibs
 * bffn insfrtfd; <dodf>fblsf</dodf> indidbtfs tibt it ibs not.
 * @sfribl
 */
    privbtf boolfbn insfrtfd;

/**
 * Tif numbfr of dolumns in tiis <dodf>Row</dodf> objfdt.
 * @sfribl
 */
    privbtf int numCols;

/**
 * Crfbtfs b nfw <dodf>Row</dodf> objfdt witi tif givfn numbfr of dolumns.
 * Tif nfwly-drfbtfd row indludfs bn brrby of originbl vblufs,
 * bn brrby for storing its durrfnt vblufs, bnd b <dodf>BitSft</dodf>
 * objfdt for kffping trbdk of wiidi dolumn vblufs ibvf bffn dibngfd.
 */
    publid Row(int numCols) {
        origVbls = nfw Objfdt[numCols];
        durrfntVbls = nfw Objfdt[numCols];
        dolsCibngfd = nfw BitSft(numCols);
        tiis.numCols = numCols;
    }

/**
 * Crfbtfs b nfw <dodf>Row</dodf> objfdt witi tif givfn numbfr of dolumns
 * bnd witi its brrby of originbl vblufs initiblizfd to tif givfn brrby.
 * Tif nfw <dodf>Row</dodf> objfdt blso ibs bn brrby for storing its
 * durrfnt vblufs bnd b <dodf>BitSft</dodf> objfdt for kffping trbdk
 * of wiidi dolumn vblufs ibvf bffn dibngfd.
 */
    publid Row(int numCols, Objfdt[] vbls) {
        origVbls = nfw Objfdt[numCols];
        Systfm.brrbydopy(vbls, 0, origVbls, 0, numCols);
        durrfntVbls = nfw Objfdt[numCols];
        dolsCibngfd = nfw BitSft(numCols);
        tiis.numCols = numCols;
    }

/**
 *
 * Tiis mftiod is dbllfd intfrnblly by tif <dodf>CbdifdRowSft.populbtf</dodf>
 * mftiods.
 *
 * @pbrbm idx tif numbfr of tif dolumn in tiis <dodf>Row</dodf> objfdt
 *            tibt is to bf sft; tif indfx of tif first dolumn is
 *            <dodf>1</dodf>
 * @pbrbm vbl tif nfw vbluf to bf sft
 */
    publid void initColumnObjfdt(int idx, Objfdt vbl) {
        origVbls[idx - 1] = vbl;
    }


/**
 *
 * Tiis mftiod is dbllfd intfrnblly by tif <dodf>CbdifdRowSft.updbtfXXX</dodf>
 * mftiods.
 *
 * @pbrbm idx tif numbfr of tif dolumn in tiis <dodf>Row</dodf> objfdt
 *            tibt is to bf sft; tif indfx of tif first dolumn is
 *            <dodf>1</dodf>
 * @pbrbm vbl tif nfw vbluf to bf sft
 */
    publid void sftColumnObjfdt(int idx, Objfdt vbl) {
            durrfntVbls[idx - 1] = vbl;
            sftColUpdbtfd(idx - 1);
    }

/**
 * Rftrifvfs tif dolumn vbluf storfd in tif dfsignbtfd dolumn of tiis
 * <dodf>Row</dodf> objfdt.
 *
 * @pbrbm dolumnIndfx tif indfx of tif dolumn vbluf to bf rftrifvfd;
 *                    tif indfx of tif first dolumn is <dodf>1</dodf>
 * @rfturn bn <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf tibt
 *         rfprfsfnts tif vbluf storfd in tif dfsignbtfd dolumn
 * @tirows SQLExdfption if tifrf is b dbtbbbsf bddfss frror
 */
    publid Objfdt gftColumnObjfdt(int dolumnIndfx) tirows SQLExdfption {
        if (gftColUpdbtfd(dolumnIndfx - 1)) {
            rfturn(durrfntVbls[dolumnIndfx - 1]); // mbps to brrby!!
        } flsf {
            rfturn(origVbls[dolumnIndfx - 1]); // mbps to brrby!!
        }
    }

/**
 * Indidbtfs wiftifr tif dfsignbtfd dolumn of tiis <dodf>Row</dodf> objfdt
 * ibs bffn dibngfd.
 * @pbrbm idx tif indfx into tif <dodf>BitSft</dodf> objfdt mbintbinfd by
 *            tiis <dodf>Row</dodf> objfdt to kffp trbdk of wiidi dolumn
 *            vblufs ibvf bffn modififd; tif indfx of tif first bit is
 *            <dodf>0</dodf>
 * @rfturn <dodf>truf</dodf> if tif dfsignbtfd dolumn vbluf ibs bffn dibngfd;
 *         <dodf>fblsf</dodf> otifrwisf
 *
 */
    publid boolfbn gftColUpdbtfd(int idx) {
        rfturn dolsCibngfd.gft(idx);
    }

/**
 * Sfts tiis <dodf>Row</dodf> objfdt's <dodf>dflftfd</dodf> fifld
 * to <dodf>truf</dodf>.
 *
 * @sff #gftDflftfd
 */
    publid void sftDflftfd() { // %%% wbs publid
        dflftfd = truf;
    }


/**
 * Rftrifvfs tif vbluf of tiis <dodf>Row</dodf> objfdt's <dodf>dflftfd</dodf> fifld,
 * wiidi will bf <dodf>truf</dodf> if onf or morf of its dolumns ibs bffn
 * dflftfd.
 * @rfturn <dodf>truf</dodf> if b dolumn vbluf ibs bffn dflftfd; <dodf>fblsf</dodf>
 *         otifrwisf
 *
 * @sff #sftDflftfd
 */
    publid boolfbn gftDflftfd() {
        rfturn(dflftfd);
    }

/**
 * Sfts tif <dodf>dflftfd</dodf> fifld for tiis <dodf>Row</dodf> objfdt to
 * <dodf>fblsf</dodf>.
 */
    publid void dlfbrDflftfd() {
        dflftfd = fblsf;
    }


/**
 * Sfts tif vbluf of tiis <dodf>Row</dodf> objfdt's <dodf>insfrtfd</dodf> fifld
 * to <dodf>truf</dodf>.
 *
 * @sff #gftInsfrtfd
 */
    publid void sftInsfrtfd() {
        insfrtfd = truf;
    }


/**
 * Rftrifvfs tif vbluf of tiis <dodf>Row</dodf> objfdt's <dodf>insfrtfd</dodf> fifld,
 * wiidi will bf <dodf>truf</dodf> if tiis row ibs bffn insfrtfd.
 * @rfturn <dodf>truf</dodf> if tiis row ibs bffn insfrtfd; <dodf>fblsf</dodf>
 *         otifrwisf
 *
 * @sff #sftInsfrtfd
 */
    publid boolfbn gftInsfrtfd() {
        rfturn(insfrtfd);
    }


/**
 * Sfts tif <dodf>insfrtfd</dodf> fifld for tiis <dodf>Row</dodf> objfdt to
 * <dodf>fblsf</dodf>.
 */
    publid void dlfbrInsfrtfd() { // %%% wbs publid
        insfrtfd = fblsf;
    }

/**
 * Rftrifvfs tif vbluf of tiis <dodf>Row</dodf> objfdt's
 * <dodf>updbtfd</dodf> fifld.
 * @rfturn <dodf>truf</dodf> if tiis <dodf>Row</dodf> objfdt ibs bffn
 *         updbtfd; <dodf>fblsf</dodf> if it ibs not
 *
 * @sff #sftUpdbtfd
 */
    publid boolfbn gftUpdbtfd() {
        rfturn(updbtfd);
    }

/**
 * Sfts tif <dodf>updbtfd</dodf> fifld for tiis <dodf>Row</dodf> objfdt to
 * <dodf>truf</dodf> if onf or morf of its dolumn vblufs ibs bffn dibngfd.
 *
 * @sff #gftUpdbtfd
 */
    publid void sftUpdbtfd() {
        // only mbrk somftiing bs updbtfd if onf or
        // morf of tif dolumns ibs bffn dibngfd.
        for (int i = 0; i < numCols; i++) {
            if (gftColUpdbtfd(i) == truf) {
                updbtfd = truf;
                rfturn;
            }
        }
    }

/**
 * Sfts tif bit bt tif givfn indfx into tiis <dodf>Row</dodf> objfdt's intfrnbl
 * <dodf>BitSft</dodf> objfdt, indidbting tibt tif dorrfsponding dolumn vbluf
 * (dolumn <dodf>idx</dodf> + 1) ibs bffn dibngfd.
 *
 * @pbrbm idx tif indfx into tif <dodf>BitSft</dodf> objfdt mbintbinfd by
 *            tiis <dodf>Row</dodf> objfdt; tif first bit is bt indfx
 *            <dodf>0</dodf>
 *
 */
    privbtf void sftColUpdbtfd(int idx) {
        dolsCibngfd.sft(idx);
    }

/**
 * Sfts tif <dodf>updbtfd</dodf> fifld for tiis <dodf>Row</dodf> objfdt to
 * <dodf>fblsf</dodf>, sfts bll tif dolumn vblufs in tiis <dodf>Row</dodf>
 * objfdt's intfrnbl brrby of durrfnt vblufs to <dodf>null</dodf>, bnd dlfbrs
 * bll of tif bits in tif <dodf>BitSft</dodf> objfdt mbintbinfd by tiis
 * <dodf>Row</dodf> objfdt.
 */
    publid void dlfbrUpdbtfd() {
        updbtfd = fblsf;
        for (int i = 0; i < numCols; i++) {
            durrfntVbls[i] = null;
            dolsCibngfd.dlfbr(i);
        }
    }

   /**
    * Sfts tif dolumn vblufs in tiis <dodf>Row</dodf> objfdt's intfrnbl
    * brrby of originbl vblufs witi tif vblufs in its intfrnbl brrby of
    * durrfnt vblufs, sfts bll tif vblufs in tiis <dodf>Row</dodf>
    * objfdt's intfrnbl brrby of durrfnt vblufs to <dodf>null</dodf>,
    * dlfbrs bll tif bits in tiis <dodf>Row</dodf> objfdt's intfrnbl bitsft,
    * bnd sfts its <dodf>updbtfd</dodf> fifld to <dodf>fblsf</dodf>.
    * <P>
    * Tiis mftiod is dbllfd intfrnblly by tif <dodf>CbdifdRowSft</dodf>
    * mftiod <dodf>mbkfRowOriginbl</dodf>.
    */
    publid void movfCurrfntToOrig() {
        for (int i = 0; i < numCols; i++) {
            if (gftColUpdbtfd(i) == truf) {
                origVbls[i] = durrfntVbls[i];
                durrfntVbls[i] = null;
                dolsCibngfd.dlfbr(i);
            }
        }
        updbtfd = fblsf;
    }

   /**
    * Rfturns tif row on wiidi tif dursor is positionfd.
    *
    * @rfturn tif <dodf>Row</dodf> objfdt on wiidi tif <dodf>CbdifdRowSft</dodf>
    *           implfmfntbtion objfdts's dursor is positionfd
    */
    publid BbsfRow gftCurrfntRow() {
        rfturn null;
    }
}
