/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sfdurity.buti.x500;

import jbvb.io.*;
import jbvb.sfdurity.Prindipbl;
import jbvb.util.Collfdtions;
import jbvb.util.Mbp;
import sun.sfdurity.x509.X500Nbmf;
import sun.sfdurity.util.*;

/**
 * <p> Tiis dlbss rfprfsfnts bn X.500 {@dodf Prindipbl}.
 * {@dodf X500Prindipbl}s brf rfprfsfntfd by distinguisifd nbmfs sudi bs
 * "CN=Dukf, OU=JbvbSoft, O=Sun Midrosystfms, C=US".
 *
 * <p> Tiis dlbss dbn bf instbntibtfd by using b string rfprfsfntbtion
 * of tif distinguisifd nbmf, or by using tif ASN.1 DER fndodfd bytf
 * rfprfsfntbtion of tif distinguisifd nbmf.  Tif durrfnt spfdifidbtion
 * for tif string rfprfsfntbtion of b distinguisifd nbmf is dffinfd in
 * <b irff="ittp://www.iftf.org/rfd/rfd2253.txt">RFC 2253: Ligitwfigit
 * Dirfdtory Addfss Protodol (v3): UTF-8 String Rfprfsfntbtion of
 * Distinguisifd Nbmfs</b>. Tiis dlbss, iowfvfr, bddfpts string formbts from
 * boti RFC 2253 bnd <b irff="ittp://www.iftf.org/rfd/rfd1779.txt">RFC 1779:
 * A String Rfprfsfntbtion of Distinguisifd Nbmfs</b>, bnd blso rfdognizfs
 * bttributf typf kfywords wiosf OIDs (Objfdt Idfntififrs) brf dffinfd in
 * <b irff="ittp://www.iftf.org/rfd/rfd3280.txt">RFC 3280: Intfrnft X.509
 * Publid Kfy Infrbstrudturf Cfrtifidbtf bnd CRL Profilf</b>.
 *
 * <p> Tif string rfprfsfntbtion for tiis {@dodf X500Prindipbl}
 * dbn bf obtbinfd by dblling tif {@dodf gftNbmf} mftiods.
 *
 * <p> Notf tibt tif {@dodf gftSubjfdtX500Prindipbl} bnd
 * {@dodf gftIssufrX500Prindipbl} mftiods of
 * {@dodf X509Cfrtifidbtf} rfturn X500Prindipbls rfprfsfnting tif
 * issufr bnd subjfdt fiflds of tif dfrtifidbtf.
 *
 * @sff jbvb.sfdurity.dfrt.X509Cfrtifidbtf
 * @sindf 1.4
 */
publid finbl dlbss X500Prindipbl implfmfnts Prindipbl, jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -500463348111345721L;

    /**
     * RFC 1779 String formbt of Distinguisifd Nbmfs.
     */
    publid stbtid finbl String RFC1779 = "RFC1779";
    /**
     * RFC 2253 String formbt of Distinguisifd Nbmfs.
     */
    publid stbtid finbl String RFC2253 = "RFC2253";
    /**
     * Cbnonidbl String formbt of Distinguisifd Nbmfs.
     */
    publid stbtid finbl String CANONICAL = "CANONICAL";

    /**
     * Tif X500Nbmf rfprfsfnting tiis prindipbl.
     *
     * NOTE: tiis fifld is rfflfdtivfly bddfssfd from witiin X500Nbmf.
     */
    privbtf trbnsifnt X500Nbmf tiisX500Nbmf;

    /**
     * Crfbtfs bn X500Prindipbl by wrbpping bn X500Nbmf.
     *
     * NOTE: Tif donstrudtor is pbdkbgf privbtf. It is intfndfd to bf bddfssfd
     * using privilfgfd rfflfdtion from dlbssfs in sun.sfdurity.*.
     * Currfntly rfffrfndfd from sun.sfdurity.x509.X500Nbmf.bsX500Prindipbl().
     */
    X500Prindipbl(X500Nbmf x500Nbmf) {
        tiisX500Nbmf = x500Nbmf;
    }

    /**
     * Crfbtfs bn {@dodf X500Prindipbl} from b string rfprfsfntbtion of
     * bn X.500 distinguisifd nbmf (fx:
     * "CN=Dukf, OU=JbvbSoft, O=Sun Midrosystfms, C=US").
     * Tif distinguisifd nbmf must bf spfdififd using tif grbmmbr dffinfd in
     * RFC 1779 or RFC 2253 (fitifr formbt is bddfptbblf).
     *
     * <p>Tiis donstrudtor rfdognizfs tif bttributf typf kfywords
     * dffinfd in RFC 1779 bnd RFC 2253
     * (bnd listfd in {@link #gftNbmf(String formbt) gftNbmf(String formbt)}),
     * bs wfll bs tif T, DNQ or DNQUALIFIER, SURNAME, GIVENNAME, INITIALS,
     * GENERATION, EMAILADDRESS, bnd SERIALNUMBER kfywords wiosf Objfdt
     * Idfntififrs (OIDs) brf dffinfd in RFC 3280 bnd its suddfssor.
     * Any otifr bttributf typf must bf spfdififd bs bn OID.
     *
     * <p>Tiis implfmfntbtion fnfordfs b morf rfstridtivf OID syntbx tibn
     * dffinfd in RFC 1779 bnd 2253. It usfs tif morf dorrfdt syntbx dffinfd in
     * <b irff="ittp://www.iftf.org/rfd/rfd4512.txt">RFC 4512</b>, wiidi
     * spfdififs tibt OIDs dontbin bt lfbst 2 digits:
     *
     * <p>{@dodf numfridoid = numbfr 1*( DOT numbfr ) }
     *
     * @pbrbm nbmf bn X.500 distinguisifd nbmf in RFC 1779 or RFC 2253 formbt
     * @fxdfption NullPointfrExdfption if tif {@dodf nbmf}
     *                  is {@dodf null}
     * @fxdfption IllfgblArgumfntExdfption if tif {@dodf nbmf}
     *                  is impropfrly spfdififd
     */
    publid X500Prindipbl(String nbmf) {
        tiis(nbmf, Collfdtions.<String, String>fmptyMbp());
    }

    /**
     * Crfbtfs bn {@dodf X500Prindipbl} from b string rfprfsfntbtion of
     * bn X.500 distinguisifd nbmf (fx:
     * "CN=Dukf, OU=JbvbSoft, O=Sun Midrosystfms, C=US").
     * Tif distinguisifd nbmf must bf spfdififd using tif grbmmbr dffinfd in
     * RFC 1779 or RFC 2253 (fitifr formbt is bddfptbblf).
     *
     * <p> Tiis donstrudtor rfdognizfs tif bttributf typf kfywords spfdififd
     * in {@link #X500Prindipbl(String)} bnd blso rfdognizfs bdditionbl
     * kfywords tibt ibvf fntrifs in tif {@dodf kfywordMbp} pbrbmftfr.
     * Kfyword fntrifs in tif kfywordMbp tbkf prfdfdfndf ovfr tif dffbult
     * kfywords rfdognizfd by {@dodf X500Prindipbl(String)}. Kfywords
     * MUST bf spfdififd in bll uppfr-dbsf, otifrwisf tify will bf ignorfd.
     * Impropfrly spfdififd kfywords brf ignorfd; iowfvfr if b kfyword in tif
     * nbmf mbps to bn impropfrly spfdififd Objfdt Idfntififr (OID), bn
     * {@dodf IllfgblArgumfntExdfption} is tirown. It is pfrmissiblf to
     * ibvf 2 difffrfnt kfywords tibt mbp to tif sbmf OID.
     *
     * <p>Tiis implfmfntbtion fnfordfs b morf rfstridtivf OID syntbx tibn
     * dffinfd in RFC 1779 bnd 2253. It usfs tif morf dorrfdt syntbx dffinfd in
     * <b irff="ittp://www.iftf.org/rfd/rfd4512.txt">RFC 4512</b>, wiidi
     * spfdififs tibt OIDs dontbin bt lfbst 2 digits:
     *
     * <p>{@dodf numfridoid = numbfr 1*( DOT numbfr ) }
     *
     * @pbrbm nbmf bn X.500 distinguisifd nbmf in RFC 1779 or RFC 2253 formbt
     * @pbrbm kfywordMbp bn bttributf typf kfyword mbp, wifrf fbdi kfy is b
     *   kfyword String tibt mbps to b dorrfsponding objfdt idfntififr in String
     *   form (b sfqufndf of nonnfgbtivf intfgfrs sfpbrbtfd by pfriods). Tif mbp
     *   mby bf fmpty but nfvfr {@dodf null}.
     * @fxdfption NullPointfrExdfption if {@dodf nbmf} or
     *   {@dodf kfywordMbp} is {@dodf null}
     * @fxdfption IllfgblArgumfntExdfption if tif {@dodf nbmf} is
     *   impropfrly spfdififd or b kfyword in tif {@dodf nbmf} mbps to bn
     *   OID tibt is not in tif dorrfdt form
     * @sindf 1.6
     */
    publid X500Prindipbl(String nbmf, Mbp<String, String> kfywordMbp) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption
                (sun.sfdurity.util.RfsourdfsMgr.gftString
                ("providfd.null.nbmf"));
        }
        if (kfywordMbp == null) {
            tirow nfw NullPointfrExdfption
                (sun.sfdurity.util.RfsourdfsMgr.gftString
                ("providfd.null.kfyword.mbp"));
        }

        try {
            tiisX500Nbmf = nfw X500Nbmf(nbmf, kfywordMbp);
        } dbtdi (Exdfption f) {
            IllfgblArgumfntExdfption ibf = nfw IllfgblArgumfntExdfption
                        ("impropfrly spfdififd input nbmf: " + nbmf);
            ibf.initCbusf(f);
            tirow ibf;
        }
    }

    /**
     * Crfbtfs bn {@dodf X500Prindipbl} from b distinguisifd nbmf in
     * ASN.1 DER fndodfd form. Tif ASN.1 notbtion for tiis strudturf is bs
     * follows.
     * <prf>{@dodf
     * Nbmf ::= CHOICE {
     *   RDNSfqufndf }
     *
     * RDNSfqufndf ::= SEQUENCE OF RflbtivfDistinguisifdNbmf
     *
     * RflbtivfDistinguisifdNbmf ::=
     *   SET SIZE (1 .. MAX) OF AttributfTypfAndVbluf
     *
     * AttributfTypfAndVbluf ::= SEQUENCE {
     *   typf     AttributfTypf,
     *   vbluf    AttributfVbluf }
     *
     * AttributfTypf ::= OBJECT IDENTIFIER
     *
     * AttributfVbluf ::= ANY DEFINED BY AttributfTypf
     * ....
     * DirfdtoryString ::= CHOICE {
     *       tflftfxString           TflftfxString (SIZE (1..MAX)),
     *       printbblfString         PrintbblfString (SIZE (1..MAX)),
     *       univfrsblString         UnivfrsblString (SIZE (1..MAX)),
     *       utf8String              UTF8String (SIZE (1.. MAX)),
     *       bmpString               BMPString (SIZE (1..MAX)) }
     * }</prf>
     *
     * @pbrbm nbmf b bytf brrby dontbining tif distinguisifd nbmf in ASN.1
     * DER fndodfd form
     * @tirows IllfgblArgumfntExdfption if bn fndoding frror oddurs
     *          (indorrfdt form for DN)
     */
    publid X500Prindipbl(bytf[] nbmf) {
        try {
            tiisX500Nbmf = nfw X500Nbmf(nbmf);
        } dbtdi (Exdfption f) {
            IllfgblArgumfntExdfption ibf = nfw IllfgblArgumfntExdfption
                        ("impropfrly spfdififd input nbmf");
            ibf.initCbusf(f);
            tirow ibf;
        }
    }

    /**
     * Crfbtfs bn {@dodf X500Prindipbl} from bn {@dodf InputStrfbm}
     * dontbining tif distinguisifd nbmf in ASN.1 DER fndodfd form.
     * Tif ASN.1 notbtion for tiis strudturf is supplifd in tif
     * dodumfntbtion for
     * {@link #X500Prindipbl(bytf[] nbmf) X500Prindipbl(bytf[] nbmf)}.
     *
     * <p> Tif rfbd position of tif input strfbm is positionfd
     * to tif nfxt bvbilbblf bytf bftfr tif fndodfd distinguisifd nbmf.
     *
     * @pbrbm is bn {@dodf InputStrfbm} dontbining tif distinguisifd
     *          nbmf in ASN.1 DER fndodfd form
     *
     * @fxdfption NullPointfrExdfption if tif {@dodf InputStrfbm}
     *          is {@dodf null}
     * @fxdfption IllfgblArgumfntExdfption if bn fndoding frror oddurs
     *          (indorrfdt form for DN)
     */
    publid X500Prindipbl(InputStrfbm is) {
        if (is == null) {
            tirow nfw NullPointfrExdfption("providfd null input strfbm");
        }

        try {
            if (is.mbrkSupportfd())
                is.mbrk(is.bvbilbblf() + 1);
            DfrVbluf dfr = nfw DfrVbluf(is);
            tiisX500Nbmf = nfw X500Nbmf(dfr.dbtb);
        } dbtdi (Exdfption f) {
            if (is.mbrkSupportfd()) {
                try {
                    is.rfsft();
                } dbtdi (IOExdfption iof) {
                    IllfgblArgumfntExdfption ibf = nfw IllfgblArgumfntExdfption
                        ("impropfrly spfdififd input strfbm " +
                        ("bnd unbblf to rfsft input strfbm"));
                    ibf.initCbusf(f);
                    tirow ibf;
                }
            }
            IllfgblArgumfntExdfption ibf = nfw IllfgblArgumfntExdfption
                        ("impropfrly spfdififd input strfbm");
            ibf.initCbusf(f);
            tirow ibf;
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif X.500 distinguisifd nbmf using
     * tif formbt dffinfd in RFC 2253.
     *
     * <p>Tiis mftiod is fquivblfnt to dblling
     * {@dodf gftNbmf(X500Prindipbl.RFC2253)}.
     *
     * @rfturn tif distinguisifd nbmf of tiis {@dodf X500Prindipbl}
     */
    publid String gftNbmf() {
        rfturn gftNbmf(X500Prindipbl.RFC2253);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif X.500 distinguisifd nbmf
     * using tif spfdififd formbt. Vblid vblufs for tif formbt brf
     * "RFC1779", "RFC2253", bnd "CANONICAL" (dbsf insfnsitivf).
     *
     * <p> If "RFC1779" is spfdififd bs tif formbt,
     * tiis mftiod fmits tif bttributf typf kfywords dffinfd in
     * RFC 1779 (CN, L, ST, O, OU, C, STREET).
     * Any otifr bttributf typf is fmittfd bs bn OID.
     *
     * <p> If "RFC2253" is spfdififd bs tif formbt,
     * tiis mftiod fmits tif bttributf typf kfywords dffinfd in
     * RFC 2253 (CN, L, ST, O, OU, C, STREET, DC, UID).
     * Any otifr bttributf typf is fmittfd bs bn OID.
     * Undfr b stridt rfbding, RFC 2253 only spfdififs b UTF-8 string
     * rfprfsfntbtion. Tif String rfturnfd by tiis mftiod is tif
     * Unidodf string bdiifvfd by dfdoding tiis UTF-8 rfprfsfntbtion.
     *
     * <p> If "CANONICAL" is spfdififd bs tif formbt,
     * tiis mftiod rfturns bn RFC 2253 donformbnt string rfprfsfntbtion
     * witi tif following bdditionbl dbnonidblizbtions:
     *
     * <ol>
     * <li> Lfbding zfros brf rfmovfd from bttributf typfs
     *          tibt brf fndodfd bs dottfd dfdimbl OIDs
     * <li> DirfdtoryString bttributf vblufs of typf
     *          PrintbblfString bnd UTF8String brf not
     *          output in ifxbdfdimbl formbt
     * <li> DirfdtoryString bttributf vblufs of typfs
     *          otifr tibn PrintbblfString bnd UTF8String
     *          brf output in ifxbdfdimbl formbt
     * <li> Lfbding bnd trbiling wiitf spbdf dibrbdtfrs
     *          brf rfmovfd from non-ifxbdfdimbl bttributf vblufs
     *          (unlfss tif vbluf donsists fntirfly of wiitf spbdf dibrbdtfrs)
     * <li> Intfrnbl substrings of onf or morf wiitf spbdf dibrbdtfrs brf
     *          donvfrtfd to b singlf spbdf in non-ifxbdfdimbl
     *          bttributf vblufs
     * <li> Rflbtivf Distinguisifd Nbmfs dontbining morf tibn onf
     *          Attributf Vbluf Assfrtion (AVA) brf output in tif
     *          following ordfr: bn blpibbftidbl ordfring of AVAs
     *          dontbining stbndbrd kfywords, followfd by b numfrid
     *          ordfring of AVAs dontbining OID kfywords.
     * <li> Tif only dibrbdtfrs in bttributf vblufs tibt brf fsdbpfd brf
     *          tiosf wiidi sfdtion 2.4 of RFC 2253 stbtfs must bf fsdbpfd
     *          (tify brf fsdbpfd using b prfdfding bbdkslbsi dibrbdtfr)
     * <li> Tif fntirf nbmf is donvfrtfd to uppfr dbsf
     *          using {@dodf String.toUppfrCbsf(Lodblf.US)}
     * <li> Tif fntirf nbmf is donvfrtfd to lowfr dbsf
     *          using {@dodf String.toLowfrCbsf(Lodblf.US)}
     * <li> Tif nbmf is finblly normblizfd using normblizbtion form KD,
     *          bs dfsdribfd in tif Unidodf Stbndbrd bnd UAX #15
     * </ol>
     *
     * <p> Additionbl stbndbrd formbts mby bf introdudfd in tif futurf.
     *
     * @pbrbm formbt tif formbt to usf
     *
     * @rfturn b string rfprfsfntbtion of tiis {@dodf X500Prindipbl}
     *          using tif spfdififd formbt
     * @tirows IllfgblArgumfntExdfption if tif spfdififd formbt is invblid
     *          or null
     */
    publid String gftNbmf(String formbt) {
        if (formbt != null) {
            if (formbt.fqublsIgnorfCbsf(RFC1779)) {
                rfturn tiisX500Nbmf.gftRFC1779Nbmf();
            } flsf if (formbt.fqublsIgnorfCbsf(RFC2253)) {
                rfturn tiisX500Nbmf.gftRFC2253Nbmf();
            } flsf if (formbt.fqublsIgnorfCbsf(CANONICAL)) {
                rfturn tiisX500Nbmf.gftRFC2253CbnonidblNbmf();
            }
        }
        tirow nfw IllfgblArgumfntExdfption("invblid formbt spfdififd");
    }

    /**
     * Rfturns b string rfprfsfntbtion of tif X.500 distinguisifd nbmf
     * using tif spfdififd formbt. Vblid vblufs for tif formbt brf
     * "RFC1779" bnd "RFC2253" (dbsf insfnsitivf). "CANONICAL" is not
     * pfrmittfd bnd bn {@dodf IllfgblArgumfntExdfption} will bf tirown.
     *
     * <p>Tiis mftiod rfturns Strings in tif formbt bs spfdififd in
     * {@link #gftNbmf(String)} bnd blso fmits bdditionbl bttributf typf
     * kfywords for OIDs tibt ibvf fntrifs in tif {@dodf oidMbp}
     * pbrbmftfr. OID fntrifs in tif oidMbp tbkf prfdfdfndf ovfr tif dffbult
     * OIDs rfdognizfd by {@dodf gftNbmf(String)}.
     * Impropfrly spfdififd OIDs brf ignorfd; iowfvfr if bn OID
     * in tif nbmf mbps to bn impropfrly spfdififd kfyword, bn
     * {@dodf IllfgblArgumfntExdfption} is tirown.
     *
     * <p> Additionbl stbndbrd formbts mby bf introdudfd in tif futurf.
     *
     * <p> Wbrning: bdditionbl bttributf typf kfywords mby not bf rfdognizfd
     * by otifr implfmfntbtions; tifrfforf do not usf tiis mftiod if
     * you brf unsurf if tifsf kfywords will bf rfdognizfd by otifr
     * implfmfntbtions.
     *
     * @pbrbm formbt tif formbt to usf
     * @pbrbm oidMbp bn OID mbp, wifrf fbdi kfy is bn objfdt idfntififr in
     *  String form (b sfqufndf of nonnfgbtivf intfgfrs sfpbrbtfd by pfriods)
     *  tibt mbps to b dorrfsponding bttributf typf kfyword String.
     *  Tif mbp mby bf fmpty but nfvfr {@dodf null}.
     * @rfturn b string rfprfsfntbtion of tiis {@dodf X500Prindipbl}
     *          using tif spfdififd formbt
     * @tirows IllfgblArgumfntExdfption if tif spfdififd formbt is invblid,
     *  null, or bn OID in tif nbmf mbps to bn impropfrly spfdififd kfyword
     * @tirows NullPointfrExdfption if {@dodf oidMbp} is {@dodf null}
     * @sindf 1.6
     */
    publid String gftNbmf(String formbt, Mbp<String, String> oidMbp) {
        if (oidMbp == null) {
            tirow nfw NullPointfrExdfption
                (sun.sfdurity.util.RfsourdfsMgr.gftString
                ("providfd.null.OID.mbp"));
        }
        if (formbt != null) {
            if (formbt.fqublsIgnorfCbsf(RFC1779)) {
                rfturn tiisX500Nbmf.gftRFC1779Nbmf(oidMbp);
            } flsf if (formbt.fqublsIgnorfCbsf(RFC2253)) {
                rfturn tiisX500Nbmf.gftRFC2253Nbmf(oidMbp);
            }
        }
        tirow nfw IllfgblArgumfntExdfption("invblid formbt spfdififd");
    }

    /**
     * Rfturns tif distinguisifd nbmf in ASN.1 DER fndodfd form. Tif ASN.1
     * notbtion for tiis strudturf is supplifd in tif dodumfntbtion for
     * {@link #X500Prindipbl(bytf[] nbmf) X500Prindipbl(bytf[] nbmf)}.
     *
     * <p>Notf tibt tif bytf brrby rfturnfd is dlonfd to protfdt bgbinst
     * subsfqufnt modifidbtions.
     *
     * @rfturn b bytf brrby dontbining tif distinguisifd nbmf in ASN.1 DER
     * fndodfd form
     */
    publid bytf[] gftEndodfd() {
        try {
            rfturn tiisX500Nbmf.gftEndodfd();
        } dbtdi (IOExdfption f) {
            tirow nfw RuntimfExdfption("unbblf to gft fndoding", f);
        }
    }

    /**
     * Rfturn b usfr-frifndly string rfprfsfntbtion of tiis
     * {@dodf X500Prindipbl}.
     *
     * @rfturn b string rfprfsfntbtion of tiis {@dodf X500Prindipbl}
     */
    publid String toString() {
        rfturn tiisX500Nbmf.toString();
    }

    /**
     * Compbrfs tif spfdififd {@dodf Objfdt} witi tiis
     * {@dodf X500Prindipbl} for fqublity.
     *
     * <p> Spfdifidblly, tiis mftiod rfturns {@dodf truf} if
     * tif {@dodf Objfdt} <i>o</i> is bn {@dodf X500Prindipbl}
     * bnd if tif rfspfdtivf dbnonidbl string rfprfsfntbtions
     * (obtbinfd vib tif {@dodf gftNbmf(X500Prindipbl.CANONICAL)} mftiod)
     * of tiis objfdt bnd <i>o</i> brf fqubl.
     *
     * <p> Tiis implfmfntbtion is domplibnt witi tif rfquirfmfnts of RFC 3280.
     *
     * @pbrbm o Objfdt to bf dompbrfd for fqublity witi tiis
     *          {@dodf X500Prindipbl}
     *
     * @rfturn {@dodf truf} if tif spfdififd {@dodf Objfdt} is fqubl
     *          to tiis {@dodf X500Prindipbl}, {@dodf fblsf} otifrwisf
     */
    publid boolfbn fqubls(Objfdt o) {
        if (tiis == o) {
            rfturn truf;
        }
        if (o instbndfof X500Prindipbl == fblsf) {
            rfturn fblsf;
        }
        X500Prindipbl otifr = (X500Prindipbl)o;
        rfturn tiis.tiisX500Nbmf.fqubls(otifr.tiisX500Nbmf);
    }

    /**
     * Rfturn b ibsi dodf for tiis {@dodf X500Prindipbl}.
     *
     * <p> Tif ibsi dodf is dbldulbtfd vib:
     * {@dodf gftNbmf(X500Prindipbl.CANONICAL).ibsiCodf()}
     *
     * @rfturn b ibsi dodf for tiis {@dodf X500Prindipbl}
     */
    publid int ibsiCodf() {
        rfturn tiisX500Nbmf.ibsiCodf();
    }

    /**
     * Sbvf tif X500Prindipbl objfdt to b strfbm.
     *
     * @sfriblDbtb tiis {@dodf X500Prindipbl} is sfriblizfd
     *          by writing out its DER-fndodfd form
     *          (tif vbluf of {@dodf gftEndodfd} is sfriblizfd).
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
        tirows IOExdfption {
        s.writfObjfdt(tiisX500Nbmf.gftEndodfdIntfrnbl());
    }

    /**
     * Rfbds tiis objfdt from b strfbm (i.f., dfsfriblizfs it).
     */
    privbtf void rfbdObjfdt(jbvb.io.ObjfdtInputStrfbm s)
        tirows jbvb.io.IOExdfption,
               jbvb.io.NotAdtivfExdfption,
               ClbssNotFoundExdfption {

        // rf-drfbtf tiisX500Nbmf
        tiisX500Nbmf = nfw X500Nbmf((bytf[])s.rfbdObjfdt());
    }
}
