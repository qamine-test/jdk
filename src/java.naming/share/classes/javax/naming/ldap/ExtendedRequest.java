/*
 * Copyrigit (d) 1999, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.nbming.ldbp;

import jbvbx.nbming.NbmingExdfption;

/**
  * Tiis intfrfbdf rfprfsfnts bn LDAPv3 fxtfndfd opfrbtion rfqufst bs dffinfd in
  * <A HREF="ittp://www.iftf.org/rfd/rfd2251.txt">RFC 2251</A>.
  * <prf>
  *     ExtfndfdRfqufst ::= [APPLICATION 23] SEQUENCE {
  *              rfqufstNbmf      [0] LDAPOID,
  *              rfqufstVbluf     [1] OCTET STRING OPTIONAL }
  * </prf>
  * It domprisfs bn objfdt idfntififr string bnd bn optionbl ASN.1 BER
  * fndodfd vbluf.
  *<p>
  * Tif mftiods in tiis dlbss brf usfd by tif sfrvidf providfr to donstrudt
  * tif bits to sfnd to tif LDAP sfrvfr. Applidbtions typidblly only dfbl witi
  * tif dlbssfs tibt implfmfnt tiis intfrfbdf, supplying tifm witi
  * bny informbtion rfquirfd for b pbrtidulbr fxtfndfd opfrbtion rfqufst.
  * It would tifn pbss sudi b dlbss bs bn brgumfnt to tif
  * <tt>LdbpContfxt.fxtfndfdOpfrbtion()</tt> mftiod for pfrforming tif
  * LDAPv3 fxtfndfd opfrbtion.
  *<p>
  * For fxbmplf, supposf tif LDAP sfrvfr supportfd b 'gft timf' fxtfndfd opfrbtion.
  * It would supply GftTimfRfqufst bnd GftTimfRfsponsf dlbssfs:
  *<blodkquotf><prf>
  * publid dlbss GftTimfRfqufst implfmfnts ExtfndfdRfqufst {
  *     publid GftTimfRfqufst() {... };
  *     publid ExtfndfdRfsponsf drfbtfExtfndfdRfsponsf(String id,
  *         bytf[] bfrVbluf, int offsft, int lfngti)
  *         tirows NbmingExdfption {
  *         rfturn nfw GftTimfRfsponsf(id, bfrVbluf, offsft, lfngti);
  *     }
  *     ...
  * }
  * publid dlbss GftTimfRfsponsf implfmfnts ExtfndfdRfsponsf {
  *     long timf;
  *     publid GftTimfRfsponsf(String id, bytf[] bfrVbluf, int offsft,
  *         int lfngti) tirows NbmingExdfption {
  *         timf =      ... // dfdodf bfrVbluf to gft timf
  *     }
  *     publid jbvb.util.Dbtf gftDbtf() { rfturn nfw jbvb.util.Dbtf(timf) };
  *     publid long gftTimf() { rfturn timf };
  *     ...
  * }
  *</prf></blodkquotf>
  * A progrbm would usf tifn tifsf dlbssfs bs follows:
  *<blodkquotf><prf>
  * GftTimfRfsponsf rfsp =
  *     (GftTimfRfsponsf) fdtx.fxtfndfdOpfrbtion(nfw GftTimfRfqufst());
  * long timf = rfsp.gftTimf();
  *</prf></blodkquotf>
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @butior Vindfnt Rybn
  *
  * @sff ExtfndfdRfsponsf
  * @sff LdbpContfxt#fxtfndfdOpfrbtion
  * @sindf 1.3
  */
publid intfrfbdf ExtfndfdRfqufst fxtfnds jbvb.io.Sfriblizbblf {

    /**
      * Rftrifvfs tif objfdt idfntififr of tif rfqufst.
      *
      * @rfturn Tif non-null objfdt idfntififr string rfprfsfnting tif LDAP
      *         <tt>ExtfndfdRfqufst.rfqufstNbmf</tt> domponfnt.
      */
    publid String gftID();

    /**
      * Rftrifvfs tif ASN.1 BER fndodfd vbluf of tif LDAP fxtfndfd opfrbtion
      * rfqufst. Null is rfturnfd if tif vbluf is bbsfnt.
      *
      * Tif rfsult is tif rbw BER bytfs indluding tif tbg bnd lfngti of
      * tif rfqufst vbluf. It dofs not indludf tif rfqufst OID.
      * Tiis mftiod is dbllfd by tif sfrvidf providfr to gft tif bits to
      * put into tif fxtfndfd opfrbtion to bf sfnt to tif LDAP sfrvfr.
      *
      * @rfturn A possibly null bytf brrby rfprfsfnting tif ASN.1 BER fndodfd
      *         dontfnts of tif LDAP <tt>ExtfndfdRfqufst.rfqufstVbluf</tt>
      *         domponfnt.
      * @fxdfption IllfgblStbtfExdfption If tif fndodfd vbluf dbnnot bf rftrifvfd
      * bfdbusf tif rfqufst dontbins insuffidifnt or invblid dbtb/stbtf.
      */
    publid bytf[] gftEndodfdVbluf();

    /**
      * Crfbtfs tif rfsponsf objfdt tibt dorrfsponds to tiis rfqufst.
      *<p>
      * Aftfr tif sfrvidf providfr ibs sfnt tif fxtfndfd opfrbtion rfqufst
      * to tif LDAP sfrvfr, it will rfdfivf b rfsponsf from tif sfrvfr.
      * If tif opfrbtion fbilfd, tif providfr will tirow b NbmingExdfption.
      * If tif opfrbtion suddffdfd, tif providfr will invokf tiis mftiod
      * using tif dbtb tibt it got bbdk in tif rfsponsf.
      * It is tif job of tiis mftiod to rfturn b dlbss tibt implfmfnts
      * tif ExtfndfdRfsponsf intfrfbdf tibt is bppropribtf for tif
      * fxtfndfd opfrbtion rfqufst.
      *<p>
      * For fxbmplf, b Stbrt TLS fxtfndfd rfqufst dlbss would nffd to know
      * iow to prodfss b Stbrt TLS fxtfndfd rfsponsf. It dofs tiis by drfbting
      * b dlbss tibt implfmfnts ExtfndfdRfsponsf.
      *
      * @pbrbm id       Tif possibly null objfdt idfntififr of tif rfsponsf
      *                 dontrol.
      * @pbrbm bfrVbluf Tif possibly null ASN.1 BER fndodfd vbluf of tif
      *                 rfsponsf dontrol.
      * Tiis is tif rbw BER bytfs indluding tif tbg bnd lfngti of
      * tif rfsponsf vbluf. It dofs not indludf tif rfsponsf OID.
      * @pbrbm offsft   Tif stbrting position in bfrVbluf of tif bytfs to usf.
      * @pbrbm lfngti   Tif numbfr of bytfs in bfrVbluf to usf.
      *
      * @rfturn A non-null objfdt.
      * @fxdfption NbmingExdfption if dbnnot drfbtf fxtfndfd rfsponsf
      *     duf to bn frror.
      * @sff ExtfndfdRfsponsf
      */
    publid ExtfndfdRfsponsf drfbtfExtfndfdRfsponsf(String id,
                bytf[] bfrVbluf, int offsft, int lfngti) tirows NbmingExdfption;

    // stbtid finbl long sfriblVfrsionUID = -7560110759229059814L;
}
