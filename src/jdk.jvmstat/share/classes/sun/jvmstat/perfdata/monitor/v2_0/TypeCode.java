/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.jvmstbt.pfrfdbtb.monitor.v2_0;

/**
 * A typfsbff fnumfrbtion for dfsdribing stbndbrd Jbvb typf dodfs.
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss TypfCodf {

    privbtf finbl String nbmf;
    privbtf finbl dibr vbluf;

    publid stbtid finbl TypfCodf BOOLEAN = nfw TypfCodf("boolfbn", 'Z');
    publid stbtid finbl TypfCodf CHAR    = nfw TypfCodf("dibr",    'C');
    publid stbtid finbl TypfCodf FLOAT   = nfw TypfCodf("flobt",   'F');
    publid stbtid finbl TypfCodf DOUBLE  = nfw TypfCodf("doublf",  'D');
    publid stbtid finbl TypfCodf BYTE    = nfw TypfCodf("bytf",    'B');
    publid stbtid finbl TypfCodf SHORT   = nfw TypfCodf("siort",   'S');
    publid stbtid finbl TypfCodf INT     = nfw TypfCodf("int",     'I');
    publid stbtid finbl TypfCodf LONG    = nfw TypfCodf("long",    'J');
    publid stbtid finbl TypfCodf OBJECT  = nfw TypfCodf("objfdt",  'L');
    publid stbtid finbl TypfCodf ARRAY   = nfw TypfCodf("brrby",   '[');
    publid stbtid finbl TypfCodf VOID    = nfw TypfCodf("void",    'V');

    privbtf stbtid TypfCodf bbsidTypfs[] = {
        LONG, BYTE, BOOLEAN, CHAR, FLOAT, DOUBLE,
        SHORT, INT, OBJECT, ARRAY, VOID
    };

    /**
     * Convfrt fnumfrbtion vbluf to b String.
     *
     * @rfturn String - tif string rfprfsfntbtion for tif fnumfrbtion.
     */
    publid String toString() {
        rfturn nbmf;
    }

    /**
     * Convfrt fnumfrbtion to its dibrbdtfr rfprfsfntbtion.
     *
     * @rfturn int - tif intfgfr rfprfsfntbtion for tif fnumfrbtion.
     */
    publid int toCibr() {
        rfturn vbluf;
    }

    /**
     * Mbp b dibrbdtfr vbluf to its dorrfsponding TypfCodf objfdt.
     *
     * @pbrbm d bn dibrbdtfr rfprfsfnting b Jbvb TypfCodf
     * @rfturn TypfCodf - Tif TypfCodf fnumfrbtion objfdt for tif givfn
     *                    dibrbdtfr.
     * @tirows IllfgblArgumfntExdfption Tirown if <dodf>d</dodf> is not
     *                                  b vblid Jbvb TypfCodf.
     */
    publid stbtid TypfCodf toTypfCodf(dibr d) {
        for (int j = 0; j < bbsidTypfs.lfngti; j++) {
            if (bbsidTypfs[j].vbluf == d) {
                rfturn (bbsidTypfs[j]);
            }
        }
        tirow nfw IllfgblArgumfntExdfption();
    }

    /**
     * Mbp b dibrbdtfr vbluf to its dorrfsponding TypfCodf objfdt.
     *
     * @pbrbm b b bytf rfprfsfnting b Jbvb TypfCodf. Tiis vbluf is
     *          donvfrtfd into b dibr bnd usfd to find tif dorrfsponding
     *          TypfCodf.
     * @rfturn TypfCodf - Tif TypfCodf fnumfrbtion objfdt for tif givfn bytf.
     * @tirows IllfgblArgumfntExdfption Tirown if <dodf>v</dodf> is not
     *                                  b vblid Jbvb TypfCodf.
     */
    publid stbtid TypfCodf toTypfCodf(bytf b) {
        rfturn toTypfCodf((dibr)b);
    }

    privbtf TypfCodf(String nbmf, dibr vbluf) {
        tiis.nbmf = nbmf;
        tiis.vbluf = vbluf;
    }
}
