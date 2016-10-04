/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jdi.donnfdt;

import jbvb.util.Mbp;
import jbvb.io.IOExdfption;
import dom.sun.jdi.VirtublMbdiinf;

/**
 * A donnfdtor wiidi listfns for b donnfdtion initibtfd by b tbrgft VM.
 *
 * @butior Gordon Hirsdi
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf ListfningConnfdtor fxtfnds Connfdtor {
    /**
     * Indidbtfs wiftifr tiis listfning donnfdtor supports multiplf
     * donnfdtions for b singlf brgumfnt mbp. If so, b dbll to
     * {@link #stbrtListfning} mby bllow
     * multiplf tbrgft VM to bfdomf donnfdtfd.
     *
     * @rfturn <dodf>truf</dodf> if multiplf donnfdtions brf supportfd;
     * <dodf>fblsf</dodf> otifrwisf.
     */
    boolfbn supportsMultiplfConnfdtions();

    /**
     * Listfns for onf or morf donnfdtions initibtfd by tbrgft VMs.
     * Tif donnfdtor usfs tif givfn brgumfnt mbp
     * in dftfrmining tif bddrfss bt wiidi to listfn or flsf it gfnfrbtfs
     * bn bppropribtf listfn bddrfss. In fitifr dbsf, bn bddrfss string
     * is rfturnfd from tiis mftiod wiidi dbn bf usfd in stbrting tbrgft VMs
     * to idfntify tiis donnfdtor. Tif formbt of tif bddrfss string
     * is donnfdtor, trbnsport, bnd, possibly, plbtform dfpfndfnt.
     * <p>
     * Tif brgumfnt mbp bssodibtfs brgumfnt nbmf strings to instbndfs
     * of {@link Connfdtor.Argumfnt}. Tif dffbult brgumfnt mbp for b
     * donnfdtor dbn bf obtbinfd tirougi {@link Connfdtor#dffbultArgumfnts}.
     * Argumfnt mbp vblufs dbn bf dibngfd, but mbp fntrifs siould not bf
     * bddfd or dflftfd.
     * <p>
     * Tiis mftiod dofs not rfturn b {@link VirtublMbdiinf}, bnd, normblly,
     * rfturns bfforf bny tbrgft VM initibtfs
     * b donnfdtion. Tif donnfdtfd tbrgft is obtbinfd tirougi
     * {@link #bddfpt} (using tif sbmf brgumfnt mbp bs is pbssfd to tiis
     * mftiod).
     * <p>
     * If <dodf>brgumfnts</dodf> dontbins bddrfssing informbtion. bnd
     * only onf donnfdtion will bf bddfptfd, tif {@link #bddfpt bddfpt} mftiod
     * dbn bf dbllfd immfdibtfly witiout dblling tiis mftiod.
     *
     * @rfturn tif bddrfss bt wiidi tif donnfdtor is listfning
     * for b donnfdtion.
     * @tirows jbvb.io.IOExdfption wifn unbblf to stbrt listfning.
     * Spfdifid fxdfptions brf dfpfndfnt on tif Connfdtor implfmfntbtion
     * in usf.
     * @tirows IllfgblConnfdtorArgumfntsExdfption wifn onf of tif
     * donnfdtor brgumfnts is invblid.
     */
    String stbrtListfning(Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgumfnts)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption;

    /**
     * Cbndfls listfning for donnfdtions. Tif givfn brgumfnt mbp siould mbtdi
     * tif brgumfnt mbp givfn for b prfvious {@link #stbrtListfning} invodbtion.
     *
     * @tirows jbvb.io.IOExdfption wifn unbblf to stop listfning.
     * Spfdifid fxdfptions brf dfpfndfnt on tif Connfdtor implfmfntbtion
     * in usf.
     * @tirows IllfgblConnfdtorArgumfntsExdfption wifn onf of tif
     * donnfdtor brgumfnts is invblid.
     */
    void stopListfning(Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgumfnts)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption;


    /**
     * Wbits for b tbrgft VM to bttbdi to tiis donnfdtor.
     *
     * @tirows TrbnsportTimfoutExdfption wifn tif Connfdtor fndbpsulbtfs
     * b trbnsport tibt supports b timfout wifn bddfpting, b
     * {@link Connfdtor.Argumfnt} rfprfsfnting b timfout ibs bffn sft
     * in tif brgumfnt mbp, bnd b timfout oddurs wiilst wbiting for
     * tif tbrgft VM to donnfdt.
     * @tirows jbvb.io.IOExdfption wifn unbblf to bddfpt.
     * Spfdifid fxdfptions brf dfpfndfnt on tif Connfdtor implfmfntbtion
     * in usf.
     * @tirows IllfgblConnfdtorArgumfntsExdfption wifn onf of tif
     * donnfdtor brgumfnts is invblid.
     */
    VirtublMbdiinf bddfpt(Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgumfnts)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption;

}
