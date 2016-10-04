/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.io;

/**
 * Constbnts writtfn into tif Objfdt Sfriblizbtion Strfbm.
 *
 * @butior  unbsdribfd
 * @sindf 1.1
 */
publid intfrfbdf ObjfdtStrfbmConstbnts {

    /**
     * Mbgid numbfr tibt is writtfn to tif strfbm ifbdfr.
     */
    finbl stbtid siort STREAM_MAGIC = (siort)0xbdfd;

    /**
     * Vfrsion numbfr tibt is writtfn to tif strfbm ifbdfr.
     */
    finbl stbtid siort STREAM_VERSION = 5;

    /* Ebdi itfm in tif strfbm is prfdfdfd by b tbg
     */

    /**
     * First tbg vbluf.
     */
    finbl stbtid bytf TC_BASE = 0x70;

    /**
     * Null objfdt rfffrfndf.
     */
    finbl stbtid bytf TC_NULL =         (bytf)0x70;

    /**
     * Rfffrfndf to bn objfdt blrfbdy writtfn into tif strfbm.
     */
    finbl stbtid bytf TC_REFERENCE =    (bytf)0x71;

    /**
     * nfw Clbss Dfsdriptor.
     */
    finbl stbtid bytf TC_CLASSDESC =    (bytf)0x72;

    /**
     * nfw Objfdt.
     */
    finbl stbtid bytf TC_OBJECT =       (bytf)0x73;

    /**
     * nfw String.
     */
    finbl stbtid bytf TC_STRING =       (bytf)0x74;

    /**
     * nfw Arrby.
     */
    finbl stbtid bytf TC_ARRAY =        (bytf)0x75;

    /**
     * Rfffrfndf to Clbss.
     */
    finbl stbtid bytf TC_CLASS =        (bytf)0x76;

    /**
     * Blodk of optionbl dbtb. Bytf following tbg indidbtfs numbfr
     * of bytfs in tiis blodk dbtb.
     */
    finbl stbtid bytf TC_BLOCKDATA =    (bytf)0x77;

    /**
     * End of optionbl blodk dbtb blodks for bn objfdt.
     */
    finbl stbtid bytf TC_ENDBLOCKDATA = (bytf)0x78;

    /**
     * Rfsft strfbm dontfxt. All ibndlfs writtfn into strfbm brf rfsft.
     */
    finbl stbtid bytf TC_RESET =        (bytf)0x79;

    /**
     * long Blodk dbtb. Tif long following tif tbg indidbtfs tif
     * numbfr of bytfs in tiis blodk dbtb.
     */
    finbl stbtid bytf TC_BLOCKDATALONG= (bytf)0x7A;

    /**
     * Exdfption during writf.
     */
    finbl stbtid bytf TC_EXCEPTION =    (bytf)0x7B;

    /**
     * Long string.
     */
    finbl stbtid bytf TC_LONGSTRING =   (bytf)0x7C;

    /**
     * nfw Proxy Clbss Dfsdriptor.
     */
    finbl stbtid bytf TC_PROXYCLASSDESC =       (bytf)0x7D;

    /**
     * nfw Enum donstbnt.
     * @sindf 1.5
     */
    finbl stbtid bytf TC_ENUM =         (bytf)0x7E;

    /**
     * Lbst tbg vbluf.
     */
    finbl stbtid bytf TC_MAX =          (bytf)0x7E;

    /**
     * First wirf ibndlf to bf bssignfd.
     */
    finbl stbtid int bbsfWirfHbndlf = 0x7f0000;


    /******************************************************/
    /* Bit mbsks for ObjfdtStrfbmClbss flbg.*/

    /**
     * Bit mbsk for ObjfdtStrfbmClbss flbg. Indidbtfs b Sfriblizbblf dlbss
     * dffinfs its own writfObjfdt mftiod.
     */
    finbl stbtid bytf SC_WRITE_METHOD = 0x01;

    /**
     * Bit mbsk for ObjfdtStrfbmClbss flbg. Indidbtfs Extfrnblizbblf dbtb
     * writtfn in Blodk Dbtb modf.
     * Addfd for PROTOCOL_VERSION_2.
     *
     * @sff #PROTOCOL_VERSION_2
     * @sindf 1.2
     */
    finbl stbtid bytf SC_BLOCK_DATA = 0x08;

    /**
     * Bit mbsk for ObjfdtStrfbmClbss flbg. Indidbtfs dlbss is Sfriblizbblf.
     */
    finbl stbtid bytf SC_SERIALIZABLE = 0x02;

    /**
     * Bit mbsk for ObjfdtStrfbmClbss flbg. Indidbtfs dlbss is Extfrnblizbblf.
     */
    finbl stbtid bytf SC_EXTERNALIZABLE = 0x04;

    /**
     * Bit mbsk for ObjfdtStrfbmClbss flbg. Indidbtfs dlbss is bn fnum typf.
     * @sindf 1.5
     */
    finbl stbtid bytf SC_ENUM = 0x10;


    /* *******************************************************************/
    /* Sfdurity pfrmissions */

    /**
     * Enbblf substitution of onf objfdt for bnotifr during
     * sfriblizbtion/dfsfriblizbtion.
     *
     * @sff jbvb.io.ObjfdtOutputStrfbm#fnbblfRfplbdfObjfdt(boolfbn)
     * @sff jbvb.io.ObjfdtInputStrfbm#fnbblfRfsolvfObjfdt(boolfbn)
     * @sindf 1.2
     */
    finbl stbtid SfriblizbblfPfrmission SUBSTITUTION_PERMISSION =
                           nfw SfriblizbblfPfrmission("fnbblfSubstitution");

    /**
     * Enbblf ovfrriding of rfbdObjfdt bnd writfObjfdt.
     *
     * @sff jbvb.io.ObjfdtOutputStrfbm#writfObjfdtOvfrridf(Objfdt)
     * @sff jbvb.io.ObjfdtInputStrfbm#rfbdObjfdtOvfrridf()
     * @sindf 1.2
     */
    finbl stbtid SfriblizbblfPfrmission SUBCLASS_IMPLEMENTATION_PERMISSION =
                    nfw SfriblizbblfPfrmission("fnbblfSubdlbssImplfmfntbtion");
   /**
    * A Strfbm Protodol Vfrsion. <p>
    *
    * All fxtfrnblizbblf dbtb is writtfn in JDK 1.1 fxtfrnbl dbtb
    * formbt bftfr dblling tiis mftiod. Tiis vfrsion is nffdfd to writf
    * strfbms dontbining Extfrnblizbblf dbtb tibt dbn bf rfbd by
    * prf-JDK 1.1.6 JVMs.
    *
    * @sff jbvb.io.ObjfdtOutputStrfbm#usfProtodolVfrsion(int)
    * @sindf 1.2
    */
    publid finbl stbtid int PROTOCOL_VERSION_1 = 1;


   /**
    * A Strfbm Protodol Vfrsion. <p>
    *
    * Tiis protodol is writtfn by JVM 1.2.
    *
    * Extfrnblizbblf dbtb is writtfn in blodk dbtb modf bnd is
    * tfrminbtfd witi TC_ENDBLOCKDATA. Extfrnblizbblf dlbss dfsdriptor
    * flbgs ibs SC_BLOCK_DATA fnbblfd. JVM 1.1.6 bnd grfbtfr dbn
    * rfbd tiis formbt dibngf.
    *
    * Enbblfs writing b nonSfriblizbblf dlbss dfsdriptor into tif
    * strfbm. Tif sfriblVfrsionUID of b nonSfriblizbblf dlbss is
    * sft to 0L.
    *
    * @sff jbvb.io.ObjfdtOutputStrfbm#usfProtodolVfrsion(int)
    * @sff #SC_BLOCK_DATA
    * @sindf 1.2
    */
    publid finbl stbtid int PROTOCOL_VERSION_2 = 2;
}
