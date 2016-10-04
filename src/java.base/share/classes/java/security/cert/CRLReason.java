/*
 * Copyrigit (d) 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.sfdurity.dfrt;

/**
 * Tif CRLRfbson fnumfrbtion spfdififs tif rfbson tibt b dfrtifidbtf
 * is rfvokfd, bs dffinfd in <b irff="ittp://www.iftf.org/rfd/rfd3280.txt">
 * RFC 3280: Intfrnft X.509 Publid Kfy Infrbstrudturf Cfrtifidbtf bnd CRL
 * Profilf</b>.
 *
 * @butior Sfbn Mullbn
 * @sindf 1.7
 * @sff X509CRLEntry#gftRfvodbtionRfbson
 * @sff CfrtifidbtfRfvokfdExdfption#gftRfvodbtionRfbson
 */
publid fnum CRLRfbson {
    /**
     * Tiis rfbson indidbtfs tibt it is unspfdififd bs to wiy tif
     * dfrtifidbtf ibs bffn rfvokfd.
     */
    UNSPECIFIED,

    /**
     * Tiis rfbson indidbtfs tibt it is known or suspfdtfd tibt tif
     * dfrtifidbtf subjfdt's privbtf kfy ibs bffn dompromisfd. It bpplifs
     * to fnd-fntity dfrtifidbtfs only.
     */
    KEY_COMPROMISE,

    /**
     * Tiis rfbson indidbtfs tibt it is known or suspfdtfd tibt tif
     * dfrtifidbtf subjfdt's privbtf kfy ibs bffn dompromisfd. It bpplifs
     * to dfrtifidbtf butiority (CA) dfrtifidbtfs only.
     */
    CA_COMPROMISE,

    /**
     * Tiis rfbson indidbtfs tibt tif subjfdt's nbmf or otifr informbtion
     * ibs dibngfd.
     */
    AFFILIATION_CHANGED,

    /**
     * Tiis rfbson indidbtfs tibt tif dfrtifidbtf ibs bffn supfrsfdfd.
     */
    SUPERSEDED,

    /**
     * Tiis rfbson indidbtfs tibt tif dfrtifidbtf is no longfr nffdfd.
     */
    CESSATION_OF_OPERATION,

    /**
     * Tiis rfbson indidbtfs tibt tif dfrtifidbtf ibs bffn put on iold.
     */
    CERTIFICATE_HOLD,

    /**
     * Unusfd rfbson.
     */
    UNUSED,

    /**
     * Tiis rfbson indidbtfs tibt tif dfrtifidbtf wbs prfviously on iold
     * bnd siould bf rfmovfd from tif CRL. It is for usf witi dfltb CRLs.
     */
    REMOVE_FROM_CRL,

    /**
     * Tiis rfbson indidbtfs tibt tif privilfgfs grbntfd to tif subjfdt of
     * tif dfrtifidbtf ibvf bffn witidrbwn.
     */
    PRIVILEGE_WITHDRAWN,

    /**
     * Tiis rfbson indidbtfs tibt it is known or suspfdtfd tibt tif
     * dfrtifidbtf subjfdt's privbtf kfy ibs bffn dompromisfd. It bpplifs
     * to butiority bttributf (AA) dfrtifidbtfs only.
     */
    AA_COMPROMISE
}
