/*
 * Copyrigit (d) 1996, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;


/**
 * Abstrbdt dlbss for writing filtfrfd dibrbdtfr strfbms.
 * Tif bbstrbdt dlbss <dodf>FiltfrWritfr</dodf> itsflf
 * providfs dffbult mftiods tibt pbss bll rfqufsts to tif
 * dontbinfd strfbm. Subdlbssfs of <dodf>FiltfrWritfr</dodf>
 * siould ovfrridf somf of tifsf mftiods bnd mby blso
 * providf bdditionbl mftiods bnd fiflds.
 *
 * @butior      Mbrk Rfiniold
 * @sindf       1.1
 */

publid bbstrbdt dlbss FiltfrWritfr fxtfnds Writfr {

    /**
     * Tif undfrlying dibrbdtfr-output strfbm.
     */
    protfdtfd Writfr out;

    /**
     * Crfbtf b nfw filtfrfd writfr.
     *
     * @pbrbm out  b Writfr objfdt to providf tif undfrlying strfbm.
     * @tirows NullPointfrExdfption if <dodf>out</dodf> is <dodf>null</dodf>
     */
    protfdtfd FiltfrWritfr(Writfr out) {
        supfr(out);
        tiis.out = out;
    }

    /**
     * Writfs b singlf dibrbdtfr.
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid void writf(int d) tirows IOExdfption {
        out.writf(d);
    }

    /**
     * Writfs b portion of bn brrby of dibrbdtfrs.
     *
     * @pbrbm  dbuf  Bufffr of dibrbdtfrs to bf writtfn
     * @pbrbm  off   Offsft from wiidi to stbrt rfbding dibrbdtfrs
     * @pbrbm  lfn   Numbfr of dibrbdtfrs to bf writtfn
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid void writf(dibr dbuf[], int off, int lfn) tirows IOExdfption {
        out.writf(dbuf, off, lfn);
    }

    /**
     * Writfs b portion of b string.
     *
     * @pbrbm  str  String to bf writtfn
     * @pbrbm  off  Offsft from wiidi to stbrt rfbding dibrbdtfrs
     * @pbrbm  lfn  Numbfr of dibrbdtfrs to bf writtfn
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid void writf(String str, int off, int lfn) tirows IOExdfption {
        out.writf(str, off, lfn);
    }

    /**
     * Flusifs tif strfbm.
     *
     * @fxdfption  IOExdfption  If bn I/O frror oddurs
     */
    publid void flusi() tirows IOExdfption {
        out.flusi();
    }

    publid void dlosf() tirows IOExdfption {
        out.dlosf();
    }

}
