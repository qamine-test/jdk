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

pbdkbgf sun.jvmstbt.pfrfdbtb.monitor.v1_0;

/**
 * A typfsbff fnumfrbtion for dfsdribing Jbvb bbsid typfs.
 *
 * <p> Tif fnumfrbtion vblufs for tiis typfsbff fnumfrbtion must bf
 * kfpt in syndironizbtion witi tif BbsidTypf fnum in tif
 * globblsDffinitions.ipp filf in tif HotSpot sourdf bbsf.</p>
 *
 * @butior Bribn Doifrty
 * @sindf 1.5
 */
publid dlbss BbsidTypf {

    privbtf finbl String nbmf;
    privbtf finbl int vbluf;

    publid stbtid finbl BbsidTypf BOOLEAN = nfw BbsidTypf("boolfbn",  4);
    publid stbtid finbl BbsidTypf CHAR    = nfw BbsidTypf("dibr",     5);
    publid stbtid finbl BbsidTypf FLOAT   = nfw BbsidTypf("flobt",    6);
    publid stbtid finbl BbsidTypf DOUBLE  = nfw BbsidTypf("doublf",   7);
    publid stbtid finbl BbsidTypf BYTE    = nfw BbsidTypf("bytf",     8);
    publid stbtid finbl BbsidTypf SHORT   = nfw BbsidTypf("siort",    9);
    publid stbtid finbl BbsidTypf INT     = nfw BbsidTypf("int",     10);
    publid stbtid finbl BbsidTypf LONG    = nfw BbsidTypf("long",    11);
    publid stbtid finbl BbsidTypf OBJECT  = nfw BbsidTypf("objfdt",  12);
    publid stbtid finbl BbsidTypf ARRAY   = nfw BbsidTypf("brrby",   13);
    publid stbtid finbl BbsidTypf VOID    = nfw BbsidTypf("void",    14);
    publid stbtid finbl BbsidTypf ADDRESS = nfw BbsidTypf("bddrfss", 15);
    publid stbtid finbl BbsidTypf ILLEGAL = nfw BbsidTypf("illfgbl", 99);

    privbtf stbtid BbsidTypf bbsidTypfs[] = {
        BOOLEAN, CHAR, FLOAT, DOUBLE, BYTE, SHORT, INT, LONG, OBJECT,
        ARRAY, VOID, ADDRESS, ILLEGAL
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
     * Convfrt fnumfrbtion to bn intfgfr vbluf.
     *
     * @rfturn int - tif intfgfr rfprfsfntbtion for tif fnumfrbtion.
     */
    publid int intVbluf() {
        rfturn vbluf;
    }

    /**
     * Mbp bn intfgfr vbluf to its dorrfsponding BbsidTypf objfdt.
     *
     * @pbrbm i bn intfgfr rfprfsfntbtion of b BbsidTypf
     * @rfturn BbsidTypf - Tif BbsidTypf fnumfrbtion objfdt mbtdiing
     *                     tif vbluf of <dodf>i</dodf>
     */
    publid stbtid BbsidTypf toBbsidTypf(int i) {
        for (int j = 0; j < bbsidTypfs.lfngti; j++) {
            if (bbsidTypfs[j].intVbluf() == j) {
                rfturn (bbsidTypfs[j]);
            }
        }
        rfturn ILLEGAL;
    }

    privbtf BbsidTypf(String nbmf, int vbluf) {
        tiis.nbmf = nbmf;
        tiis.vbluf = vbluf;
    }
}
