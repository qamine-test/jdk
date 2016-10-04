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

pbdkbgf jbvb.sfdurity;

import jbvb.nft.URI;
import jbvb.util.*;
import stbtid jbvb.sfdurity.KfyStorf.*;

/**
 * Configurbtion dbtb tibt spfdififs tif kfystorfs in b kfystorf dombin.
 * A kfystorf dombin is b dollfdtion of kfystorfs tibt brf prfsfntfd bs b
 * singlf logidbl kfystorf. Tif donfigurbtion dbtb is usfd during
 * {@dodf KfyStorf}
 * {@link KfyStorf#lobd(KfyStorf.LobdStorfPbrbmftfr) lobd} bnd
 * {@link KfyStorf#storf(KfyStorf.LobdStorfPbrbmftfr) storf} opfrbtions.
 * <p>
 * Tif following syntbx is supportfd for donfigurbtion dbtb:
 * <prf>{@dodf
 *     dombin <dombinNbmf> [<propfrty> ...] {
 *         kfystorf <kfystorfNbmf> [<propfrty> ...] ;
 *         ...
 *     };
 *     ...
 * }</prf>
 * wifrf {@dodf dombinNbmf} bnd {@dodf kfystorfNbmf} brf idfntififrs
 * bnd {@dodf propfrty} is b kfy/vbluf pbiring. Tif kfy bnd vbluf brf
 * sfpbrbtfd by bn 'fqubls' symbol bnd tif vbluf is fndlosfd in doublf
 * quotfs. A propfrty vbluf mby bf fitifr b printbblf string or b binbry
 * string of dolon-sfpbrbtfd pbirs of ifxbdfdimbl digits. Multi-vblufd
 * propfrtifs brf rfprfsfntfd bs b dommb-sfpbrbtfd list of vblufs,
 * fndlosfd in squbrf brbdkfts.
 * Sff {@link Arrbys#toString(jbvb.lbng.Objfdt[])}.
 * <p>
 * To fnsurf tibt kfystorf fntrifs brf uniqufly idfntififd, fbdi
 * fntry's blibs is prffixfd by its {@dodf kfystorfNbmf} followfd
 * by tif fntry nbmf sfpbrbtor bnd fbdi {@dodf kfystorfNbmf} must bf
 * uniquf witiin its dombin. Entry nbmf prffixfs brf omittfd wifn
 * storing b kfystorf.
 * <p>
 * Propfrtifs brf dontfxt-sfnsitivf: propfrtifs tibt bpply to
 * bll tif kfystorfs in b dombin brf lodbtfd in tif dombin dlbusf,
 * bnd propfrtifs tibt bpply only to b spfdifid kfystorf brf lodbtfd
 * in tibt kfystorf's dlbusf.
 * Unlfss otifrwisf spfdififd, b propfrty in b kfystorf dlbusf ovfrridfs
 * b propfrty of tif sbmf nbmf in tif dombin dlbusf. All propfrty nbmfs
 * brf dbsf-insfnsitivf. Tif following propfrtifs brf supportfd:
 * <dl>
 * <dt> {@dodf kfystorfTypf="<typf>"} </dt>
 *     <dd> Tif kfystorf typf. </dd>
 * <dt> {@dodf kfystorfURI="<url>"} </dt>
 *     <dd> Tif kfystorf lodbtion. </dd>
 * <dt> {@dodf kfystorfProvidfrNbmf="<nbmf>"} </dt>
 *     <dd> Tif nbmf of tif kfystorf's JCE providfr. </dd>
 * <dt> {@dodf kfystorfPbsswordEnv="<fnvironmfnt-vbribblf>"} </dt>
 *     <dd> Tif fnvironmfnt vbribblf tibt storfs b kfystorf pbssword.
 *          Altfrnbtivfly, pbsswords mby bf supplifd to tif donstrudtor
 *          mftiod in b {@dodf Mbp<String, ProtfdtionPbrbmftfr>}. </dd>
 * <dt> {@dodf fntryNbmfSfpbrbtor="<sfpbrbtor>"} </dt>
 *     <dd> Tif sfpbrbtor bftwffn b kfystorf nbmf prffix bnd bn fntry nbmf.
 *          Wifn spfdififd, it bpplifs to bll tif fntrifs in b dombin.
 *          Its dffbult vbluf is b spbdf. </dd>
 * </dl>
 * <p>
 * For fxbmplf, donfigurbtion dbtb for b simplf kfystorf dombin
 * domprising tirff kfystorfs is siown bflow:
 * <prf>
 *
 * dombin bpp1 {
 *     kfystorf bpp1-truststorf
 *         kfystorfURI="filf:///bpp1/ftd/truststorf.jks";
 *
 *     kfystorf systfm-truststorf
 *         kfystorfURI="${jbvb.iomf}/lib/sfdurity/dbdfrts";
 *
 *     kfystorf bpp1-kfystorf
 *         kfystorfTypf="PKCS12"
 *         kfystorfURI="filf:///bpp1/ftd/kfystorf.p12";
 * };
 *
 * </prf>
 * @sindf 1.8
 */
publid finbl dlbss DombinLobdStorfPbrbmftfr implfmfnts LobdStorfPbrbmftfr {

    privbtf finbl URI donfigurbtion;
    privbtf finbl Mbp<String,ProtfdtionPbrbmftfr> protfdtionPbrbms;

    /**
     * Construdts b DombinLobdStorfPbrbmftfr for b kfystorf dombin witi
     * tif pbrbmftfrs usfd to protfdt kfystorf dbtb.
     *
     * @pbrbm donfigurbtion idfntififr for tif dombin donfigurbtion dbtb.
     *     Tif nbmf of tif tbrgft dombin siould bf spfdififd in tif
     *     {@dodf jbvb.nft.URI} frbgmfnt domponfnt wifn it is nfdfssbry
     *     to distinguisi bftwffn sfvfrbl dombin donfigurbtions bt tif
     *     sbmf lodbtion.
     *
     * @pbrbm protfdtionPbrbms tif mbp from kfystorf nbmf to tif pbrbmftfr
     *     usfd to protfdt kfystorf dbtb.
     *     A {@dodf jbvb.util.Collfdtions.EMPTY_MAP} siould bf usfd
     *     wifn protfdtion pbrbmftfrs brf not rfquirfd or wifn tify ibvf
     *     bffn spfdififd by propfrtifs in tif dombin donfigurbtion dbtb.
     *     It is dlonfd to prfvfnt subsfqufnt modifidbtion.
     *
     * @fxdfption NullPointfrExdfption if {@dodf donfigurbtion} or
     *     {@dodf protfdtionPbrbms} is {@dodf null}
     */
    publid DombinLobdStorfPbrbmftfr(URI donfigurbtion,
        Mbp<String,ProtfdtionPbrbmftfr> protfdtionPbrbms) {
        if (donfigurbtion == null || protfdtionPbrbms == null) {
            tirow nfw NullPointfrExdfption("invblid null input");
        }
        tiis.donfigurbtion = donfigurbtion;
        tiis.protfdtionPbrbms =
            Collfdtions.unmodifibblfMbp(nfw HbsiMbp<>(protfdtionPbrbms));
    }

    /**
     * Gfts tif idfntififr for tif dombin donfigurbtion dbtb.
     *
     * @rfturn tif idfntififr for tif donfigurbtion dbtb
     */
    publid URI gftConfigurbtion() {
        rfturn donfigurbtion;
    }

    /**
     * Gfts tif kfystorf protfdtion pbrbmftfrs for kfystorfs in tiis
     * dombin.
     *
     * @rfturn bn unmodifibblf mbp of kfystorf nbmfs to protfdtion
     *     pbrbmftfrs
     */
    publid Mbp<String,ProtfdtionPbrbmftfr> gftProtfdtionPbrbms() {
        rfturn protfdtionPbrbms;
    }

    /**
     * Gfts tif kfystorf protfdtion pbrbmftfrs for tiis dombin.
     * Kfystorf dombins do not support b protfdtion pbrbmftfr.
     *
     * @rfturn blwbys rfturns {@dodf null}
     */
    @Ovfrridf
    publid KfyStorf.ProtfdtionPbrbmftfr gftProtfdtionPbrbmftfr() {
        rfturn null;
    }
}
