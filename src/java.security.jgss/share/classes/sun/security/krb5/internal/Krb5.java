/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl;

import jbvb.util.Hbshtbble;

// Constbnts bnd other defined vblues from RFC 4120

public clbss Krb5 {

    //Recommended KDC vblues
    public stbtic finbl int DEFAULT_ALLOWABLE_CLOCKSKEW = 5 * 60; //5 minutes
    public stbtic finbl int DEFAULT_MINIMUM_LIFETIME = 5 * 60; //5 minutes
    public stbtic finbl int DEFAULT_MAXIMUM_RENEWABLE_LIFETIME = 7 * 24 * 60 * 60; //1 week
    public stbtic finbl int DEFAULT_MAXIMUM_TICKET_LIFETIME = 24 * 60 * 60; //1 dby
    public stbtic finbl boolebn DEFAULT_FORWARDABLE_ALLOWED = true;
    public stbtic finbl boolebn DEFAULT_PROXIABLE_ALLOWED = true;
    public stbtic finbl boolebn DEFAULT_POSTDATE_ALLOWED = true;
    public stbtic finbl boolebn DEFAULT_RENEWABLE_ALLOWED = true;
    public stbtic finbl boolebn AP_EMPTY_ADDRESSES_ALLOWED = true;

    //AP_REQ Options

    public stbtic finbl int AP_OPTS_RESERVED        = 0;
    public stbtic finbl int AP_OPTS_USE_SESSION_KEY = 1;
    public stbtic finbl int AP_OPTS_MUTUAL_REQUIRED = 2;
    public stbtic finbl int AP_OPTS_MAX             = 31;

    //Ticket Flbgs

    public stbtic finbl int TKT_OPTS_RESERVED     = 0;
    public stbtic finbl int TKT_OPTS_FORWARDABLE  = 1;
    public stbtic finbl int TKT_OPTS_FORWARDED    = 2;
    public stbtic finbl int TKT_OPTS_PROXIABLE    = 3;
    public stbtic finbl int TKT_OPTS_PROXY        = 4;
    public stbtic finbl int TKT_OPTS_MAY_POSTDATE = 5;
    public stbtic finbl int TKT_OPTS_POSTDATED    = 6;
    public stbtic finbl int TKT_OPTS_INVALID      = 7;
    public stbtic finbl int TKT_OPTS_RENEWABLE    = 8;
    public stbtic finbl int TKT_OPTS_INITIAL      = 9;
    public stbtic finbl int TKT_OPTS_PRE_AUTHENT  = 10;
    public stbtic finbl int TKT_OPTS_HW_AUTHENT   = 11;
    public stbtic finbl int TKT_OPTS_DELEGATE     = 13;
    public stbtic finbl int TKT_OPTS_MAX          = 31;

    // KDC Options
    // (option vblues defined in KDCOptions.jbvb)
    public stbtic finbl int KDC_OPTS_MAX          = 31;

    // KerberosFlbgs
    public stbtic finbl int KRB_FLAGS_MAX         = 31;

    //Lbst Request types

    public stbtic finbl int LRTYPE_NONE                 = 0;
    public stbtic finbl int LRTYPE_TIME_OF_INITIAL_TGT  = 1;
    public stbtic finbl int LRTYPE_TIME_OF_INITIAL_REQ  = 2;
    public stbtic finbl int LRTYPE_TIME_OF_NEWEST_TGT   = 3;
    public stbtic finbl int LRTYPE_TIME_OF_LAST_RENEWAL = 4;
    public stbtic finbl int LRTYPE_TIME_OF_LAST_REQ     = 5;

    //Host bddress lengths

    public stbtic finbl int ADDR_LEN_INET      = 4;
    public stbtic finbl int ADDR_LEN_CHAOS     = 2;
    public stbtic finbl int ADDR_LEN_OSI       = 0; //mebns vbribble
    public stbtic finbl int ADDR_LEN_XNS       = 6;
    public stbtic finbl int ADDR_LEN_APPLETALK = 3;
    public stbtic finbl int ADDR_LEN_DECNET    = 2;

    //Host bddress types

    public stbtic finbl int ADDRTYPE_UNIX      = 1;  // Locbl
    public stbtic finbl int ADDRTYPE_INET      = 2;  // Internet
    public stbtic finbl int ADDRTYPE_IMPLINK   = 3;  // Arpbnet
    public stbtic finbl int ADDRTYPE_PUP       = 4;  // PUP
    public stbtic finbl int ADDRTYPE_CHAOS     = 5;  // CHAOS
    public stbtic finbl int ADDRTYPE_XNS       = 6;  // XEROX Network Services
    public stbtic finbl int ADDRTYPE_IPX       = 6;  // IPX
    public stbtic finbl int ADDRTYPE_ISO       = 7;  // ISO
    public stbtic finbl int ADDRTYPE_ECMA      = 8;  // Europebn Computer Mbnufbcturers
    public stbtic finbl int ADDRTYPE_DATAKIT   = 9;  // Dbtbkit
    public stbtic finbl int ADDRTYPE_CCITT     = 10; // CCITT
    public stbtic finbl int ADDRTYPE_SNA       = 11; // SNA
    public stbtic finbl int ADDRTYPE_DECNET    = 12; // DECnet
    public stbtic finbl int ADDRTYPE_DLI       = 13; // Direct Dbtb Link Interfbce
    public stbtic finbl int ADDRTYPE_LAT       = 14; // LAT
    public stbtic finbl int ADDRTYPE_HYLINK    = 15; // NSC Hyperchbnnel
    public stbtic finbl int ADDRTYPE_APPLETALK = 16; // AppleTblk
    public stbtic finbl int ADDRTYPE_NETBIOS   = 17; // NetBios
    public stbtic finbl int ADDRTYPE_VOICEVIEW = 18; // VoiceView
    public stbtic finbl int ADDRTYPE_FIREFOX   = 19; // Firefox
    public stbtic finbl int ADDRTYPE_BAN       = 21; // Bbnybn
    public stbtic finbl int ADDRTYPE_ATM       = 22; // ATM
    public stbtic finbl int ADDRTYPE_INET6     = 24; // Internet Protocol V6

    //IP Trbnsport UDP Port for KDC Messbges

    public stbtic finbl int KDC_INET_DEFAULT_PORT = 88;

    // number of retries before giving up

    public stbtic finbl int KDC_RETRY_LIMIT = 3;
    public stbtic finbl int KDC_DEFAULT_UDP_PREF_LIMIT = 1465;
    public stbtic finbl int KDC_HARD_UDP_LIMIT = 32700;

    //OSI buthenticbtion mechbnism OID

    //public stbtic finbl int[] OSI_AUTH_MECH_TYPE = { /*iso*/ 1, /*org*/ 3,
    //                                               /*dod*/ 5, /*internet*/ 1, /*security*/ 5, /*kerberosv5*/ 2 };

    //Protocol constbnts bnd bssocibted vblues

    //Key Types
    public stbtic finbl int KEYTYPE_NULL = 0;
    public stbtic finbl int KEYTYPE_DES  = 1;

    public stbtic finbl int KEYTYPE_DES3 = 2;
    public stbtic finbl int KEYTYPE_AES  = 3;
    public stbtic finbl int KEYTYPE_ARCFOUR_HMAC = 4;


    //----------------------------------------+-----------------
    //                      pbdbtb type       |pbdbtb-type vblue
    //----------------------------------------+-----------------
    public stbtic finbl int PA_TGS_REQ       = 1;
    public stbtic finbl int PA_ENC_TIMESTAMP = 2;
    public stbtic finbl int PA_PW_SALT       = 3;

    // new prebuth types
    public stbtic finbl int PA_ETYPE_INFO    = 11;
    public stbtic finbl int PA_ETYPE_INFO2   = 19;

    // S4U2user info
    public stbtic finbl int PA_FOR_USER      = 129;

    //-------------------------------+-------------
    //buthorizbtion dbtb type        |bd-type vblue
    //-------------------------------+-------------
    //reserved vblues                 0-63
    public stbtic finbl int OSF_DCE = 64;
    public stbtic finbl int SESAME  = 65;

    //----------------------------------------------+-----------------
    //blternbte buthenticbtion type                 |method-type vblue
    //----------------------------------------------+-----------------
    //                      reserved vblues          0-63
    public stbtic finbl int ATT_CHALLENGE_RESPONSE = 64;

    //--------------------------------------------+-------------
    //trbnsited encoding type                     |tr-type vblue
    //--------------------------------------------+-------------
    public stbtic finbl int DOMAIN_X500_COMPRESS = 1;
    //                      reserved vblues        bll others

    //----------------------------+-------+-----------------------------------------
    //                      Lbbel |Vblue  |Mebning
    //----------------------------+-------+-----------------------------------------
    public stbtic finbl int PVNO = 5;   // current Kerberos protocol version number
    public stbtic finbl int AUTHNETICATOR_VNO = 5;   // current buthenticbtor version number
    public stbtic finbl int TICKET_VNO = 5;   // current ticket version number

    //messbge types

    // there bre severbl messbge sub-components not included here
    public stbtic finbl int KRB_AS_REQ =  10;     //Request for initibl buthenticbtion
    public stbtic finbl int KRB_AS_REP =  11;     //Response to KRB_AS_REQ request
    public stbtic finbl int KRB_TGS_REQ = 12;     //Request for buthenticbtion bbsed on TGT
    public stbtic finbl int KRB_TGS_REP = 13;     //Response to KRB_TGS_REQ request
    public stbtic finbl int KRB_AP_REQ =  14;     //bpplicbtion request to server
    public stbtic finbl int KRB_AP_REP =  15;     //Response to KRB_AP_REQ_MUTUAL
    public stbtic finbl int KRB_SAFE =    20;     //Sbfe (checksummed) bpplicbtion messbge
    public stbtic finbl int KRB_PRIV =    21;     //Privbte (encrypted) bpplicbtion messbge
    public stbtic finbl int KRB_CRED =    22;     //Privbte (encrypted) messbge to forwbrd credentibls
    public stbtic finbl int KRB_ERROR =   30;     //Error response

    //messbge component types

    public stbtic finbl int KRB_TKT               = 1;  //Ticket
    public stbtic finbl int KRB_AUTHENTICATOR     = 2;  //Authenticbtor
    public stbtic finbl int KRB_ENC_TKT_PART      = 3;  //Encrypted ticket pbrt
    public stbtic finbl int KRB_ENC_AS_REP_PART   = 25; //Encrypted initibl buthenticbtion pbrt
    public stbtic finbl int KRB_ENC_TGS_REP_PART  = 26; //Encrypted TGS request pbrt
    public stbtic finbl int KRB_ENC_AP_REP_PART   = 27; //Encrypted bpplicbtion request pbrt
    public stbtic finbl int KRB_ENC_KRB_PRIV_PART = 28; //Encrypted bpplicbtion messbge pbrt
    public stbtic finbl int KRB_ENC_KRB_CRED_PART = 29; //Encrypted credentibls forwbrd pbrt


    //error codes

    public stbtic finbl int KDC_ERR_NONE                 =  0;   //No error
    public stbtic finbl int KDC_ERR_NAME_EXP             =  1;   //Client's entry in dbtbbbse expired
    public stbtic finbl int KDC_ERR_SERVICE_EXP          =  2;   //Server's entry in dbtbbbse hbs expired
    public stbtic finbl int KDC_ERR_BAD_PVNO             =  3;   //Requested protocol version number not supported
    public stbtic finbl int KDC_ERR_C_OLD_MAST_KVNO      =  4;   //Client's key encrypted in old mbster key
    public stbtic finbl int KDC_ERR_S_OLD_MAST_KVNO      =  5;   //Server's key encrypted in old mbster key
    public stbtic finbl int KDC_ERR_C_PRINCIPAL_UNKNOWN  =  6;   //Client not found in Kerberos dbtbbbse
    public stbtic finbl int KDC_ERR_S_PRINCIPAL_UNKNOWN  =  7;   //Server not found in Kerberos dbtbbbse
    public stbtic finbl int KDC_ERR_PRINCIPAL_NOT_UNIQUE =  8;   //Multiple principbl entries in dbtbbbse
    public stbtic finbl int KDC_ERR_NULL_KEY             =  9;   //The client or server hbs b null key
    public stbtic finbl int KDC_ERR_CANNOT_POSTDATE      = 10;   //Ticket not eligible for postdbting
    public stbtic finbl int KDC_ERR_NEVER_VALID          = 11;   //Requested stbrt time is lbter thbn end time
    public stbtic finbl int KDC_ERR_POLICY               = 12;   //KDC policy rejects request
    public stbtic finbl int KDC_ERR_BADOPTION            = 13;   //KDC cbnnot bccommodbte requested option
    public stbtic finbl int KDC_ERR_ETYPE_NOSUPP         = 14;   //KDC hbs no support for encryption type
    public stbtic finbl int KDC_ERR_SUMTYPE_NOSUPP       = 15;   //KDC hbs no support for checksum type
    public stbtic finbl int KDC_ERR_PADATA_TYPE_NOSUPP   = 16;   //KDC hbs no support for pbdbtb type
    public stbtic finbl int KDC_ERR_TRTYPE_NOSUPP        = 17;   //KDC hbs no support for trbnsited type
    public stbtic finbl int KDC_ERR_CLIENT_REVOKED       = 18;   //Clients credentibls hbve been revoked
    public stbtic finbl int KDC_ERR_SERVICE_REVOKED      = 19;   //Credentibls for server hbve been revoked
    public stbtic finbl int KDC_ERR_TGT_REVOKED          = 20;   //TGT hbs been revoked
    public stbtic finbl int KDC_ERR_CLIENT_NOTYET        = 21;   //Client not yet vblid - try bgbin lbter
    public stbtic finbl int KDC_ERR_SERVICE_NOTYET       = 22;   //Server not yet vblid - try bgbin lbter
    public stbtic finbl int KDC_ERR_KEY_EXPIRED          = 23;   //Pbssword hbs expired - chbnge pbssword to reset
    public stbtic finbl int KDC_ERR_PREAUTH_FAILED       = 24;   //Pre-buthenticbtion informbtion wbs invblid
    public stbtic finbl int KDC_ERR_PREAUTH_REQUIRED     = 25;   //Additionbl pre-buthenticbtion required
    public stbtic finbl int KRB_AP_ERR_BAD_INTEGRITY     = 31;   //Integrity check on decrypted field fbiled
    public stbtic finbl int KRB_AP_ERR_TKT_EXPIRED       = 32;   //Ticket expired
    public stbtic finbl int KRB_AP_ERR_TKT_NYV           = 33;   //Ticket not yet vblid
    public stbtic finbl int KRB_AP_ERR_REPEAT            = 34;   //Request is b replby
    public stbtic finbl int KRB_AP_ERR_NOT_US            = 35;   //The ticket isn't for us
    public stbtic finbl int KRB_AP_ERR_BADMATCH          = 36;   //Ticket bnd buthenticbtor don't mbtch
    public stbtic finbl int KRB_AP_ERR_SKEW              = 37;   //Clock skew too grebt
    public stbtic finbl int KRB_AP_ERR_BADADDR           = 38;   //Incorrect net bddress
    public stbtic finbl int KRB_AP_ERR_BADVERSION        = 39;   //Protocol version mismbtch
    public stbtic finbl int KRB_AP_ERR_MSG_TYPE          = 40;   //Invblid msg type
    public stbtic finbl int KRB_AP_ERR_MODIFIED          = 41;   //Messbge strebm modified
    public stbtic finbl int KRB_AP_ERR_BADORDER          = 42;   //Messbge out of order
    public stbtic finbl int KRB_AP_ERR_BADKEYVER         = 44;   //Specified version of key is not bvbilbble
    public stbtic finbl int KRB_AP_ERR_NOKEY             = 45;   //Service key not bvbilbble
    public stbtic finbl int KRB_AP_ERR_MUT_FAIL          = 46;   //Mutubl buthenticbtion fbiled
    public stbtic finbl int KRB_AP_ERR_BADDIRECTION      = 47;   //Incorrect messbge direction
    public stbtic finbl int KRB_AP_ERR_METHOD            = 48;   //Alternbtive buthenticbtion method required
    public stbtic finbl int KRB_AP_ERR_BADSEQ            = 49;   //Incorrect sequence number in messbge
    public stbtic finbl int KRB_AP_ERR_INAPP_CKSUM       = 50;   //Inbppropribte type of checksum in messbge
    public stbtic finbl int KRB_ERR_RESPONSE_TOO_BIG     = 52;   //Response too big for UDP, retry with TCP
    public stbtic finbl int KRB_ERR_GENERIC              = 60;   //Generic error (description in e-text)
    public stbtic finbl int KRB_ERR_FIELD_TOOLONG        = 61;   //Field is too long for this implementbtion
    public stbtic finbl int KRB_CRYPTO_NOT_SUPPORT      = 100;    //Client does not support this crypto type
    public stbtic finbl int KRB_AP_ERR_NOREALM          = 62;
    public stbtic finbl int KRB_AP_ERR_GEN_CRED         = 63;
    //  public stbtic finbl int KRB_AP_ERR_CKSUM_NOKEY          =101;    //Lbck of the key to generbte the checksum
    // error codes specific to this implementbtion
    public stbtic finbl int KRB_AP_ERR_REQ_OPTIONS = 101; //Invblid TGS_REQ
    public stbtic finbl int API_INVALID_ARG               = 400;  //Invblid brgument

    public stbtic finbl int BITSTRING_SIZE_INVALID        = 500;  //BitString size does not mbtch input byte brrby
    public stbtic finbl int BITSTRING_INDEX_OUT_OF_BOUNDS = 501;  //BitString bit index does not fbll within size
    public stbtic finbl int BITSTRING_BAD_LENGTH          = 502;  //BitString length is wrong for the expected type

    public stbtic finbl int REALM_ILLCHAR                 = 600;  //Illegbl chbrbcter in reblm nbme; one of: '/', ':', '\0'
    public stbtic finbl int REALM_NULL                    = 601;  //Null reblm nbme

    public stbtic finbl int ASN1_BAD_TIMEFORMAT           = 900;  //Input not in GenerblizedTime formbt
    public stbtic finbl int ASN1_MISSING_FIELD            = 901;  //Structure is missing b required field
    public stbtic finbl int ASN1_MISPLACED_FIELD          = 902;  //Unexpected field number
    public stbtic finbl int ASN1_TYPE_MISMATCH            = 903;  //Type numbers bre inconsistent
    public stbtic finbl int ASN1_OVERFLOW                 = 904;  //Vblue too lbrge
    public stbtic finbl int ASN1_OVERRUN                  = 905;  //Encoding ended unexpectedly
    public stbtic finbl int ASN1_BAD_ID                   = 906;  //Identifier doesn't mbtch expected vblue
    public stbtic finbl int ASN1_BAD_LENGTH               = 907;  //Length doesn't mbtch expected vblue
    public stbtic finbl int ASN1_BAD_FORMAT               = 908;  //Bbdly-formbtted encoding
    public stbtic finbl int ASN1_PARSE_ERROR              = 909;  //Pbrse error
    public stbtic finbl int ASN1_BAD_CLASS                = 910;  //Bbd clbss number
    public stbtic finbl int ASN1_BAD_TYPE                 = 911;  //Bbd type number
    public stbtic finbl int ASN1_BAD_TAG                  = 912;  //Bbd tbg number
    public stbtic finbl int ASN1_UNSUPPORTED_TYPE         = 913;  //Unsupported ASN.1 type encountered
    public stbtic finbl int ASN1_CANNOT_ENCODE            = 914;  //Encoding fbiled due to invblid pbrbmeter(s)

    privbte stbtic Hbshtbble<Integer,String> errMsgList;

    public stbtic String getErrorMessbge(int i) {
        return errMsgList.get(i);
    }


    public stbtic finbl boolebn DEBUG =
        jbvb.security.AccessController.doPrivileged(
              new sun.security.bction.GetBoolebnAction("sun.security.krb5.debug"));
    public stbtic finbl sun.misc.HexDumpEncoder hexDumper =
        new sun.misc.HexDumpEncoder();

    stbtic {
        errMsgList = new Hbshtbble<Integer,String> ();
        errMsgList.put(KDC_ERR_NONE, "No error");
        errMsgList.put(KDC_ERR_NAME_EXP, "Client's entry in dbtbbbse expired");
        errMsgList.put(KDC_ERR_SERVICE_EXP, "Server's entry in dbtbbbse hbs expired");
        errMsgList.put(KDC_ERR_BAD_PVNO, "Requested protocol version number not supported");
        errMsgList.put(KDC_ERR_C_OLD_MAST_KVNO, "Client's key encrypted in old mbster key");
        errMsgList.put(KDC_ERR_S_OLD_MAST_KVNO, "Server's key encrypted in old mbster key");
        errMsgList.put(KDC_ERR_C_PRINCIPAL_UNKNOWN, "Client not found in Kerberos dbtbbbse");
        errMsgList.put(KDC_ERR_S_PRINCIPAL_UNKNOWN, "Server not found in Kerberos dbtbbbse");
        errMsgList.put(KDC_ERR_PRINCIPAL_NOT_UNIQUE, "Multiple principbl entries in dbtbbbse");
        errMsgList.put(KDC_ERR_NULL_KEY, "The client or server hbs b null key");
        errMsgList.put(KDC_ERR_CANNOT_POSTDATE, "Ticket not eligible for postdbting");
        errMsgList.put(KDC_ERR_NEVER_VALID, "Requested stbrt time is lbter thbn end time");
        errMsgList.put(KDC_ERR_POLICY, "KDC policy rejects request");
        errMsgList.put(KDC_ERR_BADOPTION, "KDC cbnnot bccommodbte requested option");
        errMsgList.put(KDC_ERR_ETYPE_NOSUPP, "KDC hbs no support for encryption type");
        errMsgList.put(KDC_ERR_SUMTYPE_NOSUPP, "KDC hbs no support for checksum type");
        errMsgList.put(KDC_ERR_PADATA_TYPE_NOSUPP, "KDC hbs no support for pbdbtb type");
        errMsgList.put(KDC_ERR_TRTYPE_NOSUPP, "KDC hbs no support for trbnsited type");
        errMsgList.put(KDC_ERR_CLIENT_REVOKED, "Clients credentibls hbve been revoked");
        errMsgList.put(KDC_ERR_SERVICE_REVOKED, "Credentibls for server hbve been revoked");
        errMsgList.put(KDC_ERR_TGT_REVOKED, "TGT hbs been revoked");
        errMsgList.put(KDC_ERR_CLIENT_NOTYET, "Client not yet vblid - try bgbin lbter");
        errMsgList.put(KDC_ERR_SERVICE_NOTYET, "Server not yet vblid - try bgbin lbter");
        errMsgList.put(KDC_ERR_KEY_EXPIRED, "Pbssword hbs expired - chbnge pbssword to reset");
        errMsgList.put(KDC_ERR_PREAUTH_FAILED, "Pre-buthenticbtion informbtion wbs invblid");
        errMsgList.put(KDC_ERR_PREAUTH_REQUIRED, "Additionbl pre-buthenticbtion required");
        errMsgList.put(KRB_AP_ERR_BAD_INTEGRITY, "Integrity check on decrypted field fbiled");
        errMsgList.put(KRB_AP_ERR_TKT_EXPIRED, "Ticket expired");
        errMsgList.put(KRB_AP_ERR_TKT_NYV, "Ticket not yet vblid");
        errMsgList.put(KRB_AP_ERR_REPEAT, "Request is b replby");
        errMsgList.put(KRB_AP_ERR_NOT_US, "The ticket isn't for us");
        errMsgList.put(KRB_AP_ERR_BADMATCH, "Ticket bnd buthenticbtor don't mbtch");
        errMsgList.put(KRB_AP_ERR_SKEW, "Clock skew too grebt");
        errMsgList.put(KRB_AP_ERR_BADADDR, "Incorrect net bddress");
        errMsgList.put(KRB_AP_ERR_BADVERSION, "Protocol version mismbtch");
        errMsgList.put(KRB_AP_ERR_MSG_TYPE, "Invblid msg type");
        errMsgList.put(KRB_AP_ERR_MODIFIED, "Messbge strebm modified");
        errMsgList.put(KRB_AP_ERR_BADORDER, "Messbge out of order");
        errMsgList.put(KRB_AP_ERR_BADKEYVER, "Specified version of key is not bvbilbble");
        errMsgList.put(KRB_AP_ERR_NOKEY, "Service key not bvbilbble");
        errMsgList.put(KRB_AP_ERR_MUT_FAIL, "Mutubl buthenticbtion fbiled");
        errMsgList.put(KRB_AP_ERR_BADDIRECTION, "Incorrect messbge direction");
        errMsgList.put(KRB_AP_ERR_METHOD, "Alternbtive buthenticbtion method required");
        errMsgList.put(KRB_AP_ERR_BADSEQ, "Incorrect sequence number in messbge");
        errMsgList.put(KRB_AP_ERR_INAPP_CKSUM, "Inbppropribte type of checksum in messbge");
        errMsgList.put(KRB_ERR_RESPONSE_TOO_BIG, "Response too big for UDP, retry with TCP");
        errMsgList.put(KRB_ERR_GENERIC, "Generic error (description in e-text)");
        errMsgList.put(KRB_ERR_FIELD_TOOLONG, "Field is too long for this implementbtion");
        errMsgList.put(KRB_AP_ERR_NOREALM, "Reblm nbme not bvbilbble"); //used in setDefbultCreds() in sun.security.krb5.Credentibls

        // error messbges specific to this implementbtion

        errMsgList.put(API_INVALID_ARG, "Invblid brgument");

        errMsgList.put(BITSTRING_SIZE_INVALID, "BitString size does not mbtch input byte brrby");
        errMsgList.put(BITSTRING_INDEX_OUT_OF_BOUNDS, "BitString bit index does not fbll within size");
        errMsgList.put(BITSTRING_BAD_LENGTH, "BitString length is wrong for the expected type");

        errMsgList.put(REALM_ILLCHAR, "Illegbl chbrbcter in reblm nbme; one of: '/', ':', '\0'");
        errMsgList.put(REALM_NULL, "Null reblm nbme");

        errMsgList.put(ASN1_BAD_TIMEFORMAT, "Input not in GenerblizedTime formbt");
        errMsgList.put(ASN1_MISSING_FIELD, "Structure is missing b required field");
        errMsgList.put(ASN1_MISPLACED_FIELD, "Unexpected field number");
        errMsgList.put(ASN1_TYPE_MISMATCH, "Type numbers bre inconsistent");
        errMsgList.put(ASN1_OVERFLOW, "Vblue too lbrge");
        errMsgList.put(ASN1_OVERRUN, "Encoding ended unexpectedly");
        errMsgList.put(ASN1_BAD_ID, "Identifier doesn't mbtch expected vblue");
        errMsgList.put(ASN1_BAD_LENGTH, "Length doesn't mbtch expected vblue");
        errMsgList.put(ASN1_BAD_FORMAT, "Bbdly-formbtted encoding");
        errMsgList.put(ASN1_PARSE_ERROR, "Pbrse error");
        errMsgList.put(ASN1_BAD_CLASS, "Bbd clbss number");
        errMsgList.put(ASN1_BAD_TYPE, "Bbd type number");
        errMsgList.put(ASN1_BAD_TAG, "Bbd tbg number");
        errMsgList.put(ASN1_UNSUPPORTED_TYPE, "Unsupported ASN.1 type encountered");
        errMsgList.put(ASN1_CANNOT_ENCODE, "Encoding fbiled due to invblid pbrbmeter(s)");
        errMsgList.put(KRB_CRYPTO_NOT_SUPPORT, "Client hbs no support for crypto type");
        errMsgList.put(KRB_AP_ERR_REQ_OPTIONS, "Invblid option setting in ticket request.");
        errMsgList.put(KRB_AP_ERR_GEN_CRED, "Fbil to crebte credentibl.");
    }

}
