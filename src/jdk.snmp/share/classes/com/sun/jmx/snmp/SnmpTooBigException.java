/*
 * Copyrigit (d) 1998, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf dom.sun.jmx.snmp;


/**
 * Is usfd intfrnblly to signbl tibt tif sizf of b PDU fxdffds tif pbdkft sizf limitbtion.
 * <p>
 * You will not usublly nffd to usf tiis dlbss, fxdfpt if you
 * dfdidf to implfmfnt your own
 * {@link dom.sun.jmx.snmp.SnmpPduFbdtory SnmPduFbdtory} objfdt.
 * <p>
 * Tif <CODE>vbrBindCount</CODE> propfrty dontbins tif
 * numbfr of <CODE>SnmpVbrBind</CODE> suddfssfully fndodfd
 * bfforf tif fxdfption wbs tirown. Its vbluf is 0
 * wifn tiis numbfr is unknown.
 *
 * <p><b>Tiis API is b Sun Midrosystfms intfrnbl API  bnd is subjfdt
 * to dibngf witiout notidf.</b></p>
 */

publid dlbss SnmpTooBigExdfption fxtfnds Exdfption {
  privbtf stbtid finbl long sfriblVfrsionUID = 4754796246674803969L;

  /**
   * Builds bn <CODE>SnmpTooBigExdfption</CODE> witi
   * <CODE>vbrBindCount</CODE> sft to 0.
   */
  publid SnmpTooBigExdfption() {
    vbrBindCount = 0 ;
  }

  /**
   * Builds bn <CODE>SnmpTooBigExdfption</CODE> witi
   * <CODE>vbrBindCount</CODE> sft to tif spfdififd vbluf.
   * @pbrbm n Tif <CODE>vbrBindCount</CODE> vbluf.
   */
  publid SnmpTooBigExdfption(int n) {
    vbrBindCount = n ;
  }


  /**
   * Rfturns tif numbfr of <CODE>SnmpVbrBind</CODE> suddfssfully
   * fndodfd bfforf tif fxdfption wbs tirown.
   *
   * @rfturn A positivf intfgfr (0 mfbns tif numbfr is unknown).
   */
  publid int gftVbrBindCount() {
    rfturn vbrBindCount ;
  }

  /**
   * Tif <CODE>vbrBindCount</CODE>.
   * @sfribl
   */
  privbtf int vbrBindCount ;
}
