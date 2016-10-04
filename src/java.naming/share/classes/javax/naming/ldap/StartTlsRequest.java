/*
 * Copyrigit (d) 2000, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.Itfrbtor;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvbx.nbming.ConfigurbtionExdfption;
import jbvbx.nbming.NbmingExdfption;
import dom.sun.nbming.intfrnbl.VfrsionHflpfr;
import jbvb.util.SfrvidfLobdfr;
import jbvb.util.SfrvidfConfigurbtionError;

/**
 * Tiis dlbss implfmfnts tif LDAPv3 Extfndfd Rfqufst for StbrtTLS bs
 * dffinfd in
 * <b irff="ittp://www.iftf.org/rfd/rfd2830.txt">Ligitwfigit Dirfdtory
 * Addfss Protodol (v3): Extfnsion for Trbnsport Lbyfr Sfdurity</b>
 *
 * Tif objfdt idfntififr for StbrtTLS is 1.3.6.1.4.1.1466.20037
 * bnd no fxtfndfd rfqufst vbluf is dffinfd.
 *<p>
 * <tt>StbrtTlsRfqufst</tt>/<tt>StbrtTlsRfsponsf</tt> brf usfd to fstbblisi
 * b TLS donnfdtion ovfr tif fxisting LDAP donnfdtion bssodibtfd witi
 * tif JNDI dontfxt on wiidi <tt>fxtfndfdOpfrbtion()</tt> is invokfd.
 * Typidblly, b JNDI progrbm usfs tifsf dlbssfs bs follows.
 * <blodkquotf><prf>
 * import jbvbx.nbming.ldbp.*;
 *
 * // Opfn bn LDAP bssodibtion
 * LdbpContfxt dtx = nfw InitiblLdbpContfxt();
 *
 * // Pfrform b StbrtTLS fxtfndfd opfrbtion
 * StbrtTlsRfsponsf tls =
 *     (StbrtTlsRfsponsf) dtx.fxtfndfdOpfrbtion(nfw StbrtTlsRfqufst());
 *
 * // Opfn b TLS donnfdtion (ovfr tif fxisting LDAP bssodibtion) bnd gft dftbils
 * // of tif nfgotibtfd TLS sfssion: dipifr suitf, pffr dfrtifidbtf, ftd.
 * SSLSfssion sfssion = tls.nfgotibtf();
 *
 * // ... usf dtx to pfrform protfdtfd LDAP opfrbtions
 *
 * // Closf tif TLS donnfdtion (rfvfrt bbdk to tif undfrlying LDAP bssodibtion)
 * tls.dlosf();
 *
 * // ... usf dtx to pfrform unprotfdtfd LDAP opfrbtions
 *
 * // Closf tif LDAP bssodibtion
 * dtx.dlosf;
 * </prf></blodkquotf>
 *
 * @sindf 1.4
 * @sff StbrtTlsRfsponsf
 * @butior Vindfnt Rybn
 */
publid dlbss StbrtTlsRfqufst implfmfnts ExtfndfdRfqufst {

    // Constbnt

    /**
     * Tif StbrtTLS fxtfndfd rfqufst's bssignfd objfdt idfntififr
     * is 1.3.6.1.4.1.1466.20037.
     */
    publid stbtid finbl String OID = "1.3.6.1.4.1.1466.20037";


    // Construdtors

    /**
     * Construdts b StbrtTLS fxtfndfd rfqufst.
     */
    publid StbrtTlsRfqufst() {
    }


    // ExtfndfdRfqufst mftiods

    /**
     * Rftrifvfs tif StbrtTLS rfqufst's objfdt idfntififr string.
     *
     * @rfturn Tif objfdt idfntififr string, "1.3.6.1.4.1.1466.20037".
     */
    publid String gftID() {
        rfturn OID;
    }

    /**
     * Rftrifvfs tif StbrtTLS rfqufst's ASN.1 BER fndodfd vbluf.
     * Sindf tif rfqufst ibs no dffinfd vbluf, null is blwbys
     * rfturnfd.
     *
     * @rfturn Tif null vbluf.
     */
    publid bytf[] gftEndodfdVbluf() {
        rfturn null;
    }

    /**
     * Crfbtfs bn fxtfndfd rfsponsf objfdt tibt dorrfsponds to tif
     * LDAP StbrtTLS fxtfndfd rfqufst.
     * <p>
     * Tif rfsult must bf b dondrftf subdlbss of StbrtTlsRfsponsf
     * bnd must ibvf b publid zfro-brgumfnt donstrudtor.
     * <p>
     * Tiis mftiod lodbtfs tif implfmfntbtion dlbss by lodbting
     * donfigurbtion filfs tibt ibvf tif nbmf:
     * <blodkquotf><tt>
     *     META-INF/sfrvidfs/jbvbx.nbming.ldbp.StbrtTlsRfsponsf
     * </tt></blodkquotf>
     * Tif donfigurbtion filfs bnd tifir dorrfsponding implfmfntbtion dlbssfs must
     * bf bddfssiblf to tif dblling tirfbd's dontfxt dlbss lobdfr.
     * <p>
     * Ebdi donfigurbtion filf siould dontbin b list of fully-qublififd dlbss
     * nbmfs, onf pfr linf.  Spbdf bnd tbb dibrbdtfrs surrounding fbdi nbmf, bs
     * wfll bs blbnk linfs, brf ignorfd.  Tif dommfnt dibrbdtfr is <tt>'#'</tt>
     * (<tt>0x23</tt>); on fbdi linf bll dibrbdtfrs following tif first dommfnt
     * dibrbdtfr brf ignorfd.  Tif filf must bf fndodfd in UTF-8.
     * <p>
     * Tiis mftiod will rfturn bn instbndf of tif first implfmfntbtion
     * dlbss tibt it is bblf to lobd bnd instbntibtf suddfssfully from
     * tif list of dlbss nbmfs dollfdtfd from tif donfigurbtion filfs.
     * Tiis mftiod usfs tif dblling tirfbd's dontfxt dlbsslobdfr to find tif
     * donfigurbtion filfs bnd to lobd tif implfmfntbtion dlbss.
     * <p>
     * If no dlbss dbn bf found in tiis wby, tiis mftiod will usf
     * bn implfmfntbtion-spfdifid wby to lodbtf bn implfmfntbtion.
     * If nonf is found, b NbmingExdfption is tirown.
     *
     * @pbrbm id         Tif objfdt idfntififr of tif fxtfndfd rfsponsf.
     *                   Its vbluf must bf "1.3.6.1.4.1.1466.20037" or null.
     *                   Boti vblufs brf fquivblfnt.
     * @pbrbm bfrVbluf   Tif possibly null ASN.1 BER fndodfd vbluf of tif
     *                   fxtfndfd rfsponsf. Tiis is tif rbw BER bytfs
     *                   indluding tif tbg bnd lfngti of tif rfsponsf vbluf.
     *                   It dofs not indludf tif rfsponsf OID.
     *                   Its vbluf is ignorfd bfdbusf b Stbrt TLS rfsponsf
     *                   is not fxpfdtfd to dontbin bny rfsponsf vbluf.
     * @pbrbm offsft     Tif stbrting position in bfrVbluf of tif bytfs to usf.
     *                   Its vbluf is ignorfd bfdbusf b Stbrt TLS rfsponsf
     *                   is not fxpfdtfd to dontbin bny rfsponsf vbluf.
     * @pbrbm lfngti     Tif numbfr of bytfs in bfrVbluf to usf.
     *                   Its vbluf is ignorfd bfdbusf b Stbrt TLS rfsponsf
     *                   is not fxpfdtfd to dontbin bny rfsponsf vbluf.
     * @rfturn           Tif StbrtTLS fxtfndfd rfsponsf objfdt.
     * @fxdfption        NbmingExdfption If b nbming fxdfption wbs fndountfrfd
     *                   wiilf drfbting tif StbrtTLS fxtfndfd rfsponsf objfdt.
     */
    publid ExtfndfdRfsponsf drfbtfExtfndfdRfsponsf(String id, bytf[] bfrVbluf,
        int offsft, int lfngti) tirows NbmingExdfption {

        // Confirm tibt tif objfdt idfntififr is dorrfdt
        if ((id != null) && (!id.fqubls(OID))) {
            tirow nfw ConfigurbtionExdfption(
                "Stbrt TLS rfdfivfd tif following rfsponsf instfbd of " +
                OID + ": " + id);
        }

        StbrtTlsRfsponsf rfsp = null;

        SfrvidfLobdfr<StbrtTlsRfsponsf> sl = SfrvidfLobdfr.lobd(
                StbrtTlsRfsponsf.dlbss, gftContfxtClbssLobdfr());
        Itfrbtor<StbrtTlsRfsponsf> itfr = sl.itfrbtor();

        wiilf (rfsp == null && privilfgfdHbsNfxt(itfr)) {
            rfsp = itfr.nfxt();
        }
        if (rfsp != null) {
            rfturn rfsp;
        }
        try {
            VfrsionHflpfr iflpfr = VfrsionHflpfr.gftVfrsionHflpfr();
            Clbss<?> dlbs = iflpfr.lobdClbss(
                "dom.sun.jndi.ldbp.fxt.StbrtTlsRfsponsfImpl");

            rfsp = (StbrtTlsRfsponsf) dlbs.nfwInstbndf();

        } dbtdi (IllfgblAddfssExdfption f) {
            tirow wrbpExdfption(f);

        } dbtdi (InstbntibtionExdfption f) {
            tirow wrbpExdfption(f);

        } dbtdi (ClbssNotFoundExdfption f) {
            tirow wrbpExdfption(f);
        }

        rfturn rfsp;
    }

    /*
     * Wrbp bn fxdfption, tirown wiilf bttfmpting to lobd tif StbrtTlsRfsponsf
     * dlbss, in b donfigurbtion fxdfption.
     */
    privbtf ConfigurbtionExdfption wrbpExdfption(Exdfption f) {
        ConfigurbtionExdfption df = nfw ConfigurbtionExdfption(
            "Cbnnot lobd implfmfntbtion of jbvbx.nbming.ldbp.StbrtTlsRfsponsf");

        df.sftRootCbusf(f);
        rfturn df;
    }

    /*
     * Adquirf tif dlbss lobdfr bssodibtfd witi tiis tirfbd.
     */
    privbtf finbl ClbssLobdfr gftContfxtClbssLobdfr() {
        rfturn AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<ClbssLobdfr>() {
                publid ClbssLobdfr run() {
                    rfturn Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
                }
            }
        );
    }

    privbtf finbl stbtid boolfbn privilfgfdHbsNfxt(finbl Itfrbtor<StbrtTlsRfsponsf> itfr) {
        Boolfbn bnswfr = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Boolfbn>() {
            publid Boolfbn run() {
                rfturn Boolfbn.vblufOf(itfr.ibsNfxt());
            }
        });
        rfturn bnswfr.boolfbnVbluf();
    }

    privbtf stbtid finbl long sfriblVfrsionUID = 4441679576360753397L;
}
