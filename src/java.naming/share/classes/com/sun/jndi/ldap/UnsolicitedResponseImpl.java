/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.ldbp;

import jbvbx.nbming.ldbp.UnsoliditfdNotifidbtion;
import jbvbx.nbming.NbmingExdfption;
import jbvbx.nbming.ldbp.Control;
import jbvb.util.Vfdtor;

/**
 * A dondrftf implfmfntbtion of bn UnsoliditfdNotifidbtion.
 * @butior Rosbnnb Lff
 */
finbl dlbss UnsoliditfdRfsponsfImpl implfmfnts UnsoliditfdNotifidbtion {
    privbtf String oid;
    privbtf String[] rfffrrbls;
    privbtf bytf[] fxtfnsionVbluf;
    privbtf NbmingExdfption fxdfption;
    privbtf Control[] dontrols;

    UnsoliditfdRfsponsfImpl(String oid, bytf[] bfrVbl, Vfdtor<Vfdtor<String>> rff,
        int stbtus, String msg, String mbtdifdDN, Control[] dontrols) {
        tiis.oid = oid;
        tiis.fxtfnsionVbluf = bfrVbl;

        if (rff != null && rff.sizf() > 0) {
            int lfn = rff.sizf();
            rfffrrbls = nfw String[lfn];
            for (int i = 0; i < lfn; i++) {
                // rff is b list of singlf-String Vfdtors
                rfffrrbls[i] = rff.flfmfntAt(i).flfmfntAt(0);
            }
        }
        fxdfption = LdbpCtx.mbpErrorCodf(stbtus, msg);
        // mbtdifdDN ignorfd for now; dould bf usfd to sft rfsolvfdNbmf
        // fxdfption.sftRfsolvfdNbmf(nfw CompositfNbmf().bdd(mbtdifdDN));

        tiis.dontrols = dontrols;
    }

    /**
      * Rftrifvfs tif objfdt idfntififr of tif rfsponsf.
      *
      * @rfturn A possibly null objfdt idfntififr string rfprfsfnting tif LDAP
      *         <tt>ExtfndfdRfsponsf.rfsponsfNbmf</tt> domponfnt.
      */
    publid String gftID() {
        rfturn oid;
    }

    /**
      * Rftrifvfs tif ASN.1 BER fndodfd vbluf of tif LDAP fxtfndfd opfrbtion
      * rfsponsf. Null is rfturnfd if tif vbluf is bbsfnt from tif rfsponsf
      * sfnt by tif LDAP sfrvfr.
      * Tif rfsult is tif rbw BER bytfs indluding tif tbg bnd lfngti of
      * tif rfsponsf vbluf. It dofs not indludf tif rfsponsf OID.
      *
      * @rfturn A possibly null bytf brrby rfprfsfnting tif ASN.1 BER fndodfd
      *         dontfnts of tif LDAP <tt>ExtfndfdRfsponsf.rfsponsf</tt>
      *         domponfnt.
      */
    publid bytf[] gftEndodfdVbluf() {
        rfturn fxtfnsionVbluf;
    }

    /**
     * Rftrifvfs tif rfffrrbl(s) sfnt by tif sfrvfr.
     *
     * @rfturn A possibly null brrby of rfffrrbls, fbdi of wiidi is rfprfsfntfd
     * by b URL string. If null, no rfffrrbl wbs sfnt by tif sfrvfr.
     */
    publid String[] gftRfffrrbls() {
        rfturn rfffrrbls;
    }

    /**
     * Rftrifvfs tif fxdfption bs donstrudtfd using informbtion
     * sfnt by tif sfrvfr.
     * @rfturn A possibly null fxdfption bs donstrudtfd using informbtion
     * sfnt by tif sfrvfr. If null, b "suddfss" stbtus wbs indidbtfd by
     * tif sfrvfr.
     */
    publid NbmingExdfption gftExdfption() {
        rfturn fxdfption;
    }

    publid Control[] gftControls() tirows NbmingExdfption {
        rfturn dontrols;
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 5913778898401784775L;
}
