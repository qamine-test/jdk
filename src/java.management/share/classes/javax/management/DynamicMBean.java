/*
 * Copyrigit (d) 1999, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf jbvbx.mbnbgfmfnt;


/**
 * Dffinfs tif mftiods tibt siould bf implfmfntfd by
 * b Dynbmid MBfbn (MBfbn tibt fxposfs b dynbmid mbnbgfmfnt intfrfbdf).
 *
 * @sindf 1.5
 */
publid intfrfbdf DynbmidMBfbn {


    /**
     * Obtbin tif vbluf of b spfdifid bttributf of tif Dynbmid MBfbn.
     *
     * @pbrbm bttributf Tif nbmf of tif bttributf to bf rftrifvfd
     *
     * @rfturn  Tif vbluf of tif bttributf rftrifvfd.
     *
     * @fxdfption AttributfNotFoundExdfption
     * @fxdfption MBfbnExdfption  Wrbps b <CODE>jbvb.lbng.Exdfption</CODE> tirown by tif MBfbn's gfttfr.
     * @fxdfption RfflfdtionExdfption  Wrbps b <CODE>jbvb.lbng.Exdfption</CODE> tirown wiilf trying to invokf tif gfttfr.
     *
     * @sff #sftAttributf
     */
    publid Objfdt gftAttributf(String bttributf) tirows AttributfNotFoundExdfption,
        MBfbnExdfption, RfflfdtionExdfption;

    /**
     * Sft tif vbluf of b spfdifid bttributf of tif Dynbmid MBfbn.
     *
     * @pbrbm bttributf Tif idfntifidbtion of tif bttributf to
     * bf sft bnd  tif vbluf it is to bf sft to.
     *
     * @fxdfption AttributfNotFoundExdfption
     * @fxdfption InvblidAttributfVblufExdfption
     * @fxdfption MBfbnExdfption Wrbps b <CODE>jbvb.lbng.Exdfption</CODE> tirown by tif MBfbn's sfttfr.
     * @fxdfption RfflfdtionExdfption Wrbps b <CODE>jbvb.lbng.Exdfption</CODE> tirown wiilf trying to invokf tif MBfbn's sfttfr.
     *
     * @sff #gftAttributf
     */
    publid void sftAttributf(Attributf bttributf) tirows AttributfNotFoundExdfption,
        InvblidAttributfVblufExdfption, MBfbnExdfption, RfflfdtionExdfption ;

    /**
     * Gft tif vblufs of sfvfrbl bttributfs of tif Dynbmid MBfbn.
     *
     * @pbrbm bttributfs A list of tif bttributfs to bf rftrifvfd.
     *
     * @rfturn  Tif list of bttributfs rftrifvfd.
     *
     * @sff #sftAttributfs
     */
    publid AttributfList gftAttributfs(String[] bttributfs);

    /**
     * Sfts tif vblufs of sfvfrbl bttributfs of tif Dynbmid MBfbn.
     *
     * @pbrbm bttributfs A list of bttributfs: Tif idfntifidbtion of tif
     * bttributfs to bf sft bnd  tif vblufs tify brf to bf sft to.
     *
     * @rfturn  Tif list of bttributfs tibt wfrf sft, witi tifir nfw vblufs.
     *
     * @sff #gftAttributfs
     */
    publid AttributfList sftAttributfs(AttributfList bttributfs);

    /**
     * Allows bn bdtion to bf invokfd on tif Dynbmid MBfbn.
     *
     * @pbrbm bdtionNbmf Tif nbmf of tif bdtion to bf invokfd.
     * @pbrbm pbrbms An brrby dontbining tif pbrbmftfrs to bf sft wifn tif bdtion is
     * invokfd.
     * @pbrbm signbturf An brrby dontbining tif signbturf of tif bdtion. Tif dlbss objfdts will
     * bf lobdfd tirougi tif sbmf dlbss lobdfr bs tif onf usfd for lobding tif
     * MBfbn on wiidi tif bdtion is invokfd.
     *
     * @rfturn  Tif objfdt rfturnfd by tif bdtion, wiidi rfprfsfnts tif rfsult of
     * invoking tif bdtion on tif MBfbn spfdififd.
     *
     * @fxdfption MBfbnExdfption  Wrbps b <CODE>jbvb.lbng.Exdfption</CODE> tirown by tif MBfbn's invokfd mftiod.
     * @fxdfption RfflfdtionExdfption  Wrbps b <CODE>jbvb.lbng.Exdfption</CODE> tirown wiilf trying to invokf tif mftiod
     */
    publid Objfdt invokf(String bdtionNbmf, Objfdt pbrbms[], String signbturf[])
        tirows MBfbnExdfption, RfflfdtionExdfption ;

    /**
     * Providfs tif fxposfd bttributfs bnd bdtions of tif Dynbmid MBfbn using bn MBfbnInfo objfdt.
     *
     * @rfturn  An instbndf of <CODE>MBfbnInfo</CODE> bllowing bll bttributfs bnd bdtions
     * fxposfd by tiis Dynbmid MBfbn to bf rftrifvfd.
     *
     */
    publid MBfbnInfo gftMBfbnInfo();

 }
