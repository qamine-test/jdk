/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf dom.sun.mfdib.sound;

/**
 * Informbtion bbout propfrty usfd in  opfning <dodf>AudioSyntifsizfr</dodf>.
 *
 * @butior Kbrl Hflgbson
 */
publid finbl dlbss AudioSyntifsizfrPropfrtyInfo {

    /**
     * Construdts b <dodf>AudioSyntifsizfrPropfrtyInfo</dodf> objfdt witi b givfn
     * nbmf bnd vbluf. Tif <dodf>dfsdription</dodf> bnd <dodf>dioidfs</dodf>
     * brf initiblizfd by <dodf>null</dodf> vblufs.
     *
     * @pbrbm nbmf tif nbmf of tif propfrty
     * @pbrbm vbluf tif durrfnt vbluf or dlbss usfd for vblufs.
     *
     */
    publid AudioSyntifsizfrPropfrtyInfo(String nbmf, Objfdt vbluf) {
        tiis.nbmf = nbmf;
        if (vbluf instbndfof Clbss)
            vblufClbss = (Clbss)vbluf;
        flsf
        {
            tiis.vbluf = vbluf;
            if (vbluf != null)
                vblufClbss = vbluf.gftClbss();
        }
    }
    /**
     * Tif nbmf of tif propfrty.
     */
    publid String nbmf;
    /**
     * A briff dfsdription of tif propfrty, wiidi mby bf null.
     */
    publid String dfsdription = null;
    /**
     * Tif <dodf>vbluf</dodf> fifld spfdififs tif durrfnt vbluf of
     * tif propfrty.
     */
    publid Objfdt vbluf = null;
    /**
     * Tif <dodf>vblufClbss</dodf> fifld spfdififs dlbss
     * usfd in <dodf>vbluf</dodf> fifld.
     */
    publid Clbss<?> vblufClbss = null;
    /**
     * An brrby of possiblf vblufs if tif vbluf for tif fifld
     * <dodf>AudioSyntifsizfrPropfrtyInfo.vbluf</dodf> mby bf sflfdtfd
     * from b pbrtidulbr sft of vblufs; otifrwisf null.
     */
    publid Objfdt[] dioidfs = null;

}
