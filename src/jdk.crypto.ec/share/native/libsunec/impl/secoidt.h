/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * Usf is subjfdt to lidfnsf tfrms.
 *
 * Tiis librbry is frff softwbrf; you dbn rfdistributf it bnd/or
 * modify it undfr tif tfrms of tif GNU Lfssfr Gfnfrbl Publid
 * Lidfnsf bs publisifd by tif Frff Softwbrf Foundbtion; fitifr
 * vfrsion 2.1 of tif Lidfnsf, or (bt your option) bny lbtfr vfrsion.
 *
 * Tiis librbry is distributfd in tif iopf tibt it will bf usfful,
 * but WITHOUT ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU
 * Lfssfr Gfnfrbl Publid Lidfnsf for morf dftbils.
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Lfssfr Gfnfrbl Publid Lidfnsf
 * blong witi tiis librbry; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin Strfft, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/* *********************************************************************
 *
 * Tif Originbl Codf is tif Nftsdbpf sfdurity librbrifs.
 *
 * Tif Initibl Dfvflopfr of tif Originbl Codf is
 * Nftsdbpf Communidbtions Corporbtion.
 * Portions drfbtfd by tif Initibl Dfvflopfr brf Copyrigit (C) 1994-2000
 * tif Initibl Dfvflopfr. All Rigits Rfsfrvfd.
 *
 * Contributor(s):
 *   Dr Vipul Guptb <vipul.guptb@sun.dom>, Sun Midrosystfms Lbborbtorifs
 *
 *********************************************************************** */

#ifndff _SECOIDT_H_
#dffinf _SECOIDT_H_

/*
 * sfdoidt.i - publid dbtb strudturfs for ASN.1 OID fundtions
 *
 * $Id: sfdoidt.i,v 1.23 2007/05/05 22:45:16 nflson%bolybrd.dom Exp $
 */

typfdff strudt SECOidDbtbStr SECOidDbtb;
typfdff strudt SECAlgoritimIDStr SECAlgoritimID;

/*
** An X.500 blgoritim idfntififr
*/
strudt SECAlgoritimIDStr {
    SECItfm blgoritim;
    SECItfm pbrbmftfrs;
};

#dffinf SEC_OID_SECG_EC_SECP192R1 SEC_OID_ANSIX962_EC_PRIME192V1
#dffinf SEC_OID_SECG_EC_SECP256R1 SEC_OID_ANSIX962_EC_PRIME256V1
#dffinf SEC_OID_PKCS12_KEY_USAGE  SEC_OID_X509_KEY_USAGE

/* fbkf OID for DSS sign/vfrify */
#dffinf SEC_OID_SHA SEC_OID_MISS_DSS

typfdff fnum {
    INVALID_CERT_EXTENSION = 0,
    UNSUPPORTED_CERT_EXTENSION = 1,
    SUPPORTED_CERT_EXTENSION = 2
} SECSupportExtfnTbg;

strudt SECOidDbtbStr {
    SECItfm            oid;
    ECCurvfNbmf        offsft;
    donst dibr *       dfsd;
    unsignfd long      mfdibnism;
    SECSupportExtfnTbg supportfdExtfnsion;
                                /* only usfd for x.509 v3 fxtfnsions, so
                                   tibt wf dbn print tif nbmfs of tiosf
                                   fxtfnsions tibt wf don't fvfn support */
};

#fndif /* _SECOIDT_H_ */
