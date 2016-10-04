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

/**
  * Tiis intfrfbdf rfprfsfnts bn LDAPv3 dontrol bs dffinfd in
  * <A HREF="ittp://www.iftf.org/rfd/rfd2251.txt">RFC 2251</A>.
  *<p>
  * Tif LDAPv3 protodol usfs dontrols to sfnd bnd rfdfivf bdditionbl dbtb
  * to bfffdt tif bfibvior of prfdffinfd opfrbtions.
  * Controls dbn bf sfnt blong witi bny LDAP opfrbtion to tif sfrvfr.
  * Tifsf brf rfffrrfd to bs <fm>rfqufst dontrols</fm>. For fxbmplf, b
  * "sort" dontrol dbn bf sfnt witi bn LDAP sfbrdi opfrbtion to
  * rfqufst tibt tif rfsults bf rfturnfd in b pbrtidulbr ordfr.
  * Soliditfd bnd unsoliditfd dontrols dbn blso bf rfturnfd witi
  * rfsponsfs from tif sfrvfr. Sudi dontrols brf rfffrrfd to bs
  * <fm>rfsponsf dontrols</fm>. For fxbmplf, bn LDAP sfrvfr migit
  * dffinf b spfdibl dontrol to rfturn dibngf notifidbtions.
  *<p>
  * Tiis intfrfbdf is usfd to rfprfsfnt boti rfqufst bnd rfsponsf dontrols.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
  * @butior Vindfnt Rybn
  *
  * @sff ControlFbdtory
  * @sindf 1.3
  */
publid intfrfbdf Control fxtfnds jbvb.io.Sfriblizbblf {
    /**
      * Indidbtfs b dritidbl dontrol.
      * Tif vbluf of tiis donstbnt is <tt>truf</tt>.
      */
    publid stbtid finbl boolfbn CRITICAL = truf;

    /**
      * Indidbtfs b non-dritidbl dontrol.
      * Tif vbluf of tiis donstbnt is <tt>fblsf</tt>.
      */
    publid stbtid finbl boolfbn NONCRITICAL = fblsf;

    /**
      * Rftrifvfs tif objfdt idfntififr bssignfd for tif LDAP dontrol.
      *
      * @rfturn Tif non-null objfdt idfntififr string.
      */
    publid String gftID();

    /**
      * Dftfrminfs tif dritidblity of tif LDAP dontrol.
      * A dritidbl dontrol must not bf ignorfd by tif sfrvfr.
      * In otifr words, if tif sfrvfr rfdfivfs b dritidbl dontrol
      * tibt it dofs not support, rfgbrdlfss of wiftifr tif dontrol
      * mbkfs sfnsf for tif opfrbtion, tif opfrbtion will not bf pfrformfd
      * bnd bn <tt>OpfrbtionNotSupportfdExdfption</tt> will bf tirown.
      * @rfturn truf if tiis dontrol is dritidbl; fblsf otifrwisf.
      */
    publid boolfbn isCritidbl();

    /**
      * Rftrifvfs tif ASN.1 BER fndodfd vbluf of tif LDAP dontrol.
      * Tif rfsult is tif rbw BER bytfs indluding tif tbg bnd lfngti of
      * tif dontrol's vbluf. It dofs not indludf tif dontrols OID or dritidblity.
      *
      * Null is rfturnfd if tif vbluf is bbsfnt.
      *
      * @rfturn A possibly null bytf brrby rfprfsfnting tif ASN.1 BER fndodfd
      *         vbluf of tif LDAP dontrol.
      */
    publid bytf[] gftEndodfdVbluf();

    // stbtid finbl long sfriblVfrsionUID = -591027748900004825L;
}
