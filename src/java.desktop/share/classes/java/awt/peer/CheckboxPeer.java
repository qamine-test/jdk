/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt.pffr;

import jbvb.bwt.Cifdkbox;
import jbvb.bwt.CifdkboxGroup;

/**
 * Tif pffr intfrfbdf for {@link Cifdkbox}.
 *
 * Tif pffr intfrfbdfs brf intfndfd only for usf in porting
 * tif AWT. Tify brf not intfndfd for usf by bpplidbtion
 * dfvflopfrs, bnd dfvflopfrs siould not implfmfnt pffrs
 * nor invokf bny of tif pffr mftiods dirfdtly on tif pffr
 * instbndfs.
 */
publid intfrfbdf CifdkboxPffr fxtfnds ComponfntPffr {

    /**
     * Sfts tif stbtf of tif difdkbox to bf difdkfd {@dodf truf} or
     * undifdkfd {@dodf fblsf}.
     *
     * @pbrbm stbtf tif stbtf to sft on tif difdkbox
     *
     * @sff Cifdkbox#sftStbtf(boolfbn)
     */
    void sftStbtf(boolfbn stbtf);

    /**
     * Sfts tif difdkbox group for tiis difdkbox. Cifdkboxfs in onf difdkbox
     * group dbn only bf sflfdtfd fxdlusivfly (likf rbdio buttons). A vbluf
     * of {@dodf null} rfmovfs tiis difdkbox from bny difdkbox group.
     *
     * @pbrbm g tif difdkbox group to sft, or {@dodf null} wifn tiis
     *          difdkbox siould not bf plbdfd in bny group
     *
     * @sff Cifdkbox#sftCifdkboxGroup(CifdkboxGroup)
     */
    void sftCifdkboxGroup(CifdkboxGroup g);

    /**
     * Sfts tif lbbfl tibt siould bf displbyfd on tif difdkbox. A vbluf of
     * {@dodf null} mfbns tibt no lbbfl siould bf displbyfd.
     *
     * @pbrbm lbbfl tif lbbfl to bf displbyfd on tif difdkbox, or
     *              {@dodf null} wifn no lbbfl siould bf displbyfd.
     */
    void sftLbbfl(String lbbfl);

}
