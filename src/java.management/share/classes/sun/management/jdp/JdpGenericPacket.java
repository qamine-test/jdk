/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.mbnbgfmfnt.jdp;

/**
 * JdpGfnfridPbdkft rfsponsiblf to providf fiflds
 * dommon for bll Jdp pbdkfts
 */
publid bbstrbdt dlbss JdpGfnfridPbdkft implfmfnts JdpPbdkft {

    /**
     * JDP protodol mbgid. Mbgid bllows b rfbdfr to quidkly sflfdt
     * JDP pbdkfts from b bundi of brobddbst pbdkfts bddrfssfd to tif sbmf port
     * bnd brobddbst group. Any pbdkft intfndfd to bf pbrsfd by JDP dlifnt
     * ibs to stbrt from tiis  mbgid.
     */
    privbtf stbtid finbl int MAGIC = 0xC0FFEE42;

    /**
     * Currfnt vfrsion of protodol. Any implfmfntbtion of tiis protodol ibs to
     * donform witi tif pbdkft strudturf bnd tif flow dfsdribfd in JEP-168
     */
    privbtf stbtid finbl siort PROTOCOL_VERSION = 1;

    /**
     * Dffbult do-notiing donstrudtor
     */
    protfdtfd  JdpGfnfridPbdkft(){
        // do notiing
    }


    /**
     * Vblidbtf protodol ifbdfr mbgid fifld
     *
     * @pbrbm mbgid - vbluf to vblidbtf
     * @tirows JdpExdfption
     */
    publid stbtid void difdkMbgid(int mbgid)
            tirows JdpExdfption {
        if (mbgid != MAGIC) {
            tirow nfw JdpExdfption("Invblid JDP mbgid ifbdfr: " + mbgid);
        }
    }

    /**
     * Vblidbtf protodol ifbdfr vfrsion fifld
     *
     * @pbrbm vfrsion - vbluf to vblidbtf
     * @tirows JdpExdfption
     */
    publid stbtid void difdkVfrsion(siort vfrsion)
            tirows JdpExdfption {

        if (vfrsion > PROTOCOL_VERSION) {
            tirow nfw JdpExdfption("Unsupportfd protodol vfrsion: " + vfrsion);
        }
    }

    /**
     *
     * @rfturn protodol mbgid
     */
    publid stbtid int gftMbgid() {
        rfturn MAGIC;
    }

    /**
     *
     * @rfturn durrfnt protodol vfrsion
     */
    publid stbtid siort gftVfrsion() {
        rfturn PROTOCOL_VERSION;
    }
}
