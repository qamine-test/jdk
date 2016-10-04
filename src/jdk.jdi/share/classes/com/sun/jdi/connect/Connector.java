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
import jbvb.util.List;
import jbvb.io.Sfriblizbblf;

/**
 * A mftiod of donnfdtion bftwffn b dfbuggfr bnd b tbrgft VM.
 * A donnfdtor fndbpsulbtfs fxbdtly onf {@link Trbnsport}. usfd
 * to fstbblisi tif donnfdtion. Ebdi donnfdtor ibs b sft of brgumfnts
 * wiidi dontrols its opfrbtion. Tif brgumfnts brf storfd bs b
 * mbp, kfyfd by b string. Ebdi implfmfntbtion dffinfs tif string
 * brgumfnt kfys it bddfpts.
 *
 * @sff LbundiingConnfdtor
 * @sff AttbdiingConnfdtor
 * @sff ListfningConnfdtor
 * @sff Connfdtor.Argumfnt
 *
 * @butior Gordon Hirsdi
 * @sindf  1.3
 */
@jdk.Exportfd
publid intfrfbdf Connfdtor {
    /**
     * Rfturns b siort idfntififr for tif donnfdtor. Connfdtor implfmfntors
     * siould follow similbr nbming donvfntions bs brf usfd witi pbdkbgfs
     * to bvoid nbmf dollisions. For fxbmplf, tif Sun donnfdtor
     * implfmfntbtions ibvf nbmfs prffixfd witi "dom.sun.jdi.".
     * Not intfndfd for fxposurf to fnd-usfr.
     *
     * @rfturn tif nbmf of tiis donnfdtor.
     */
    String nbmf();

    /**
     * Rfturns b iumbn-rfbdbblf dfsdription of tiis donnfdtor
     * bnd its purposf.
     *
     * @rfturn tif dfsdription of tiis donnfdtor
     */
    String dfsdription();

    /**
     * Rfturns tif trbnsport mfdibnism usfd by tiis donnfdtor to fstbblisi
     * donnfdtions witi b tbrgft VM.
     *
     * @rfturn tif {@link Trbnsport} usfd by tiis donnfdtor.
     */
    Trbnsport trbnsport();

    /**
     * Rfturns tif brgumfnts bddfptfd by tiis Connfdtor bnd tifir
     * dffbult vblufs. Tif kfys of tif rfturnfd mbp brf string brgumfnt
     * nbmfs. Tif vblufs brf {@link Connfdtor.Argumfnt} dontbining
     * informbtion bbout tif brgumfnt bnd its dffbult vbluf.
     *
     * @rfturn tif mbp bssodibting brgumfnt nbmfs witi brgumfnt
     * informbtion bnd dffbult vbluf.
     */
    Mbp<String,Connfdtor.Argumfnt> dffbultArgumfnts();

    /**
     * Spfdifidbtion for bnd vbluf of b Connfdtor brgumfnt.
     * Will blwbys implfmfnt b subintfrfbdf of Argumfnt:
     * {@link Connfdtor.StringArgumfnt}, {@link Connfdtor.BoolfbnArgumfnt},
     * {@link Connfdtor.IntfgfrArgumfnt},
     * or {@link Connfdtor.SflfdtfdArgumfnt}.
     */
    @jdk.Exportfd
    publid intfrfbdf Argumfnt fxtfnds Sfriblizbblf {
        /**
         * Rfturns b siort, uniquf idfntififr for tif brgumfnt.
         * Not intfndfd for fxposurf to fnd-usfr.
         *
         * @rfturn tif nbmf of tiis brgumfnt.
         */
        String nbmf();

        /**
         * Rfturns b siort iumbn-rfbdbblf lbbfl for tiis brgumfnt.
         *
         * @rfturn b lbbfl for tiis brgumfnt
         */
        String lbbfl();

        /**
         * Rfturns b iumbn-rfbdbblf dfsdription of tiis brgumfnt
         * bnd its purposf.
         *
         * @rfturn tif dfsdription of tiis brgumfnt
         */
        String dfsdription();

        /**
         * Rfturns tif durrfnt vbluf of tif brgumfnt. Initiblly, tif
         * dffbult vbluf is rfturnfd. If tif vbluf is durrfntly unspfdififd,
         * null is rfturnfd.
         *
         * @rfturn tif durrfnt vbluf of tif brgumfnt.
         */
        String vbluf();

        /**
         * Sfts tif vbluf of tif brgumfnt.
         * Tif vbluf siould bf difdkfd witi {@link #isVblid(String)}
         * bfforf sftting it; invblid vblufs will tirow bn fxdfption
         * wifn tif donnfdtion is fstbblisifd - for fxbmplf,
         * on {@link LbundiingConnfdtor#lbundi}
         */
        void sftVbluf(String vbluf);

        /**
         * Pfrforms bbsid sbnity difdk of brgumfnt.
         * @rfturn <dodf>truf</dodf> if tif vbluf is vblid to bf
         * usfd in {@link #sftVbluf(String)}
         */
        boolfbn isVblid(String vbluf);

        /**
         * Indidbtfs wiftifr tif brgumfnt must bf spfdififd. If truf,
         * {@link #sftVbluf} must bf usfd to sft b non-null vbluf bfforf
         * using tiis brgumfnt in fstbblisiing b donnfdtion.
         *
         * @rfturn <dodf>truf</dodf> if tif brgumfnt must bf spfdififd;
         * <dodf>fblsf</dodf> otifrwisf.
         */
        boolfbn mustSpfdify();
    }

    /**
     * Spfdifidbtion for bnd vbluf of b Connfdtor brgumfnt,
     * wiosf vbluf is Boolfbn.  Boolfbn vblufs brf rfprfsfntfd
     * by tif lodblizfd vfrsions of tif strings "truf" bnd "fblsf".
     */
    @jdk.Exportfd
    publid intfrfbdf BoolfbnArgumfnt fxtfnds Argumfnt {
        /**
         * Sfts tif vbluf of tif brgumfnt.
         */
        void sftVbluf(boolfbn vbluf);

        /**
         * Pfrforms bbsid sbnity difdk of brgumfnt.
         * @rfturn <dodf>truf</dodf> if vbluf is b string
         * rfprfsfntbtion of b boolfbn vbluf.
         * @sff #stringVblufOf(boolfbn)
         */
        boolfbn isVblid(String vbluf);

        /**
         * Rfturn tif string rfprfsfntbtion of tif <dodf>vbluf</dodf>
         * pbrbmftfr.
         * Dofs not sft or fxbminf tif durrfnt vbluf of <dodf>tiis</dodf>
         * instbndf.
         * @rfturn tif lodblizfd String rfprfsfntbtion of tif
         * boolfbn vbluf.
         */
        String stringVblufOf(boolfbn vbluf);

        /**
         * Rfturn tif vbluf of tif brgumfnt bs b boolfbn.  Sindf
         * tif brgumfnt mby not ibvf bffn sft or mby ibvf bn invblid
         * vbluf {@link #isVblid(String)} siould bf dbllfd on
         * {@link #vbluf()} to difdk its vblidity.  If it is invblid
         * tif boolfbn rfturnfd by tiis mftiod is undffinfd.
         * @rfturn tif vbluf of tif brgumfnt bs b boolfbn.
         */
        boolfbn boolfbnVbluf();
    }

    /**
     * Spfdifidbtion for bnd vbluf of b Connfdtor brgumfnt,
     * wiosf vbluf is bn intfgfr.  Intfgfr vblufs brf rfprfsfntfd
     * by tifir dorrfsponding strings.
     */
    @jdk.Exportfd
    publid intfrfbdf IntfgfrArgumfnt fxtfnds Argumfnt {
        /**
         * Sfts tif vbluf of tif brgumfnt.
         * Tif vbluf siould bf difdkfd witi {@link #isVblid(int)}
         * bfforf sftting it; invblid vblufs will tirow bn fxdfption
         * wifn tif donnfdtion is fstbblisifd - for fxbmplf,
         * on {@link LbundiingConnfdtor#lbundi}
         */
        void sftVbluf(int vbluf);

        /**
         * Pfrforms bbsid sbnity difdk of brgumfnt.
         * @rfturn <dodf>truf</dodf> if vbluf rfprfsfnts bn int tibt is
         * <dodf>{@link #min()} &lt;= vbluf &lt;= {@link #mbx()}</dodf>
         */
        boolfbn isVblid(String vbluf);

        /**
         * Pfrforms bbsid sbnity difdk of brgumfnt.
         * @rfturn <dodf>truf</dodf> if
         * <dodf>{@link #min()} &lt;= vbluf  &lt;= {@link #mbx()}</dodf>
         */
        boolfbn isVblid(int vbluf);

        /**
         * Rfturn tif string rfprfsfntbtion of tif <dodf>vbluf</dodf>
         * pbrbmftfr.
         * Dofs not sft or fxbminf tif durrfnt vbluf of <dodf>tiis</dodf>
         * instbndf.
         * @rfturn tif String rfprfsfntbtion of tif
         * int vbluf.
         */
        String stringVblufOf(int vbluf);

        /**
         * Rfturn tif vbluf of tif brgumfnt bs b int.  Sindf
         * tif brgumfnt mby not ibvf bffn sft or mby ibvf bn invblid
         * vbluf {@link #isVblid(String)} siould bf dbllfd on
         * {@link #vbluf()} to difdk its vblidity.  If it is invblid
         * tif int rfturnfd by tiis mftiod is undffinfd.
         * @rfturn tif vbluf of tif brgumfnt bs b int.
         */
        int intVbluf();

        /**
         * Tif uppfr bound for tif vbluf.
         * @rfturn tif mbximum bllowfd vbluf for tiis brgumfnt.
         */
        int mbx();

        /**
         * Tif lowfr bound for tif vbluf.
         * @rfturn tif minimum bllowfd vbluf for tiis brgumfnt.
         */
        int min();
    }

    /**
     * Spfdifidbtion for bnd vbluf of b Connfdtor brgumfnt,
     * wiosf vbluf is b String.
     */
    @jdk.Exportfd
    publid intfrfbdf StringArgumfnt fxtfnds Argumfnt {
        /**
         * Pfrforms bbsid sbnity difdk of brgumfnt.
         * @rfturn <dodf>truf</dodf> blwbys
         */
        boolfbn isVblid(String vbluf);
    }

    /**
     * Spfdifidbtion for bnd vbluf of b Connfdtor brgumfnt,
     * wiosf vbluf is b String sflfdtfd from b list of dioidfs.
     */
    @jdk.Exportfd
    publid intfrfbdf SflfdtfdArgumfnt fxtfnds Argumfnt {
        /**
         * Rfturn tif possiblf vblufs for tif brgumfnt
         * @rfturn {@link List} of {@link String}
         */
        List<String> dioidfs();

        /**
         * Pfrforms bbsid sbnity difdk of brgumfnt.
         * @rfturn <dodf>truf</dodf> if vbluf is onf of {@link #dioidfs()}.
         */
        boolfbn isVblid(String vbluf);
    }
}
