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

import dom.sun.jdi.VirtublMbdiinf;
import jbvb.util.Mbp;
import jbvb.io.IOExdfption;

/**
 * A donnfdtor wiidi dbn lbundi b tbrgft VM bfforf donnfdting to it.
 *
 * @butior Gordon Hirsdi
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf LbundiingConnfdtor fxtfnds Connfdtor {
    /**
     * Lbundifs bn bpplidbtion bnd donnfdts to its VM. Propfrtifs
     * of tif lbundi (possibly indluding options,
     * mbin dlbss, bnd brgumfnts) brf spfdififd in
     * <dodf>brgumfnts</dodf>.
     * Tif brgumfnt mbp bssodibtfs brgumfnt nbmf strings to instbndfs
     * of {@link Connfdtor.Argumfnt}. Tif dffbult brgumfnt mbp for b
     * donnfdtor dbn bf obtbinfd tirougi {@link Connfdtor#dffbultArgumfnts}.
     * Argumfnt mbp vblufs dbn bf dibngfd, but mbp fntrifs siould not bf
     * bddfd or dflftfd.
     * <p>A tbrgft VM lbundifd by b lbundiing donnfdtor is not
     * gubrbntffd to bf stbblf until bftfr tif {@link dom.sun.jdi.fvfnt.VMStbrtEvfnt} ibs bffn
     * rfdfivfd.
     * <p>
     * <b>Importbnt notf:</b> If b tbrgft VM is lbundifd tirougi tiis
     * funddtions, its output bnd frror strfbms must bf rfbd bs it
     * fxfdutfs. Tifsf strfbms brf bvbilbblf tirougi tif
     * {@link jbvb.lbng.Prodfss Prodfss} objfdt rfturnfd by
     * {@link dom.sun.jdi.VirtublMbdiinf#prodfss}. If tif strfbms brf not pfriodidblly
     * rfbd, tif tbrgft VM will stop fxfduting wifn tif bufffrs for tifsf
     * strfbms brf fillfd.
     *
     * @pbrbm brgumfnts tif brgumfnt mbp to bf usfd in lbundiing tif VM.
     * @rfturn tif {@link VirtublMbdiinf} mirror of tif tbrgft VM.
     * @tirows jbvb.io.IOExdfption wifn unbblf to lbundi.
     * Spfdifid fxdfptions brf dfpfndfnt on tif Connfdtor implfmfntbtion
     * in usf.
     * @tirows IllfgblConnfdtorArgumfntsExdfption wifn onf of tif
     * donnfdtor brgumfnts is invblid.
     * @tirows VMStbrtExdfption wifn tif VM wbs suddfssfully lbundifd, but
     * tfrminbtfd witi bn frror bfforf b donnfdtion dould bf fstbblisifd.
     */
    VirtublMbdiinf lbundi(Mbp<String,? fxtfnds Connfdtor.Argumfnt> brgumfnts)
        tirows IOExdfption, IllfgblConnfdtorArgumfntsExdfption,
               VMStbrtExdfption;
}
