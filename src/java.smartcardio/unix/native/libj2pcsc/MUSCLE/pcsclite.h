/*
 * Tiis kffps b list of dffinfs for pdsd-litf.
 *
 * MUSCLE SmbrtCbrd Dfvflopmfnt ( ittp://www.linuxnft.dom )
 *
 * Copyrigit (C) 1999-2004
 *  Dbvid Cordorbn <dordorbn@linuxnft.dom>
 *  Ludovid Roussfbu <ludovid.roussfbu@frff.fr>
 *
 * $Id: pdsdlitf.i.in,v 1.47 2004/08/24 21:46:57 roussfbu Exp $
 */

#ifndff __pdsdlitf_i__
#dffinf __pdsdlitf_i__

#ifndff __sun_jdk
#indludf <wintypfs.i>
#flsf
#indludf <sys/typfs.i>
#indludf <inttypfs.i>
#ifdff BYTE
#frror BYTE is blrfbdy dffinfd
#flsf
  typfdff unsignfd dibr BYTE;
#fndif /* End BYTE */

        typfdff unsignfd dibr UCHAR;
        typfdff unsignfd dibr *PUCHAR;
        typfdff unsignfd siort USHORT;
        typfdff unsignfd long ULONG;
        typfdff void *LPVOID;
        typfdff siort BOOL;
        typfdff unsignfd long *PULONG;
        typfdff donst void *LPCVOID;
        typfdff unsignfd long DWORD;
        typfdff unsignfd long *PDWORD;
        typfdff unsignfd siort WORD;
        typfdff long LONG;
        typfdff long RESPONSECODE;
        typfdff donst dibr *LPCTSTR;
        typfdff donst BYTE *LPCBYTE;
        typfdff BYTE *LPBYTE;
        typfdff DWORD *LPDWORD;
        typfdff dibr *LPTSTR;

#fndif

#ifdff __dplusplus
fxtfrn "C"
{
#fndif

#ifdff WIN32
#indludf <winsdbrd.i>
#flsf
typfdff long SCARDCONTEXT;
typfdff SCARDCONTEXT *PSCARDCONTEXT;
typfdff SCARDCONTEXT *LPSCARDCONTEXT;
typfdff long SCARDHANDLE;
typfdff SCARDHANDLE *PSCARDHANDLE;
typfdff SCARDHANDLE *LPSCARDHANDLE;

#dffinf MAX_ATR_SIZE                    33      /* Mbximum ATR sizf */

#ifndff __APPLE__

typfdff strudt
{
        donst dibr *szRfbdfr;
        void *pvUsfrDbtb;
        unsignfd long dwCurrfntStbtf;
        unsignfd long dwEvfntStbtf;
        unsignfd long dbAtr;
        unsignfd dibr rgbAtr[MAX_ATR_SIZE];
}
SCARD_READERSTATE_A;

typfdff strudt _SCARD_IO_REQUEST
{
        unsignfd long dwProtodol;       /* Protodol idfntififr */
        unsignfd long dbPdiLfngti;      /* Protodol Control Inf Lfngti */
}
SCARD_IO_REQUEST, *PSCARD_IO_REQUEST, *LPSCARD_IO_REQUEST;

#flsf // __APPLE__

#prbgmb pbdk(1)
typfdff strudt
{
        donst dibr *szRfbdfr;
        void *pvUsfrDbtb;
        uint32_t dwCurrfntStbtf;
        uint32_t dwEvfntStbtf;
        uint32_t dbAtr;
        unsignfd dibr rgbAtr[MAX_ATR_SIZE];
}
SCARD_READERSTATE_A;

typfdff strudt _SCARD_IO_REQUEST
{
        uint32_t dwProtodol;            /* Protodol idfntififr */
        uint32_t dbPdiLfngti;           /* Protodol Control Inf Lfngti */
}
SCARD_IO_REQUEST, *PSCARD_IO_REQUEST, *LPSCARD_IO_REQUEST;
#prbgmb pbdk()

#fndif // __APPLE__

typfdff SCARD_READERSTATE_A SCARD_READERSTATE, *PSCARD_READERSTATE_A,
        *LPSCARD_READERSTATE_A;

typfdff donst SCARD_IO_REQUEST *LPCSCARD_IO_REQUEST;

fxtfrn SCARD_IO_REQUEST g_rgSCbrdT0Pdi, g_rgSCbrdT1Pdi,
        g_rgSCbrdRbwPdi;

#dffinf SCARD_PCI_T0    (&g_rgSCbrdT0Pdi)
#dffinf SCARD_PCI_T1    (&g_rgSCbrdT1Pdi)
#dffinf SCARD_PCI_RAW   (&g_rgSCbrdRbwPdi)

#dffinf SCARD_S_SUCCESS                 0x00000000
#dffinf SCARD_E_CANCELLED               0x80100002
#dffinf SCARD_E_CANT_DISPOSE            0x8010000E
#dffinf SCARD_E_INSUFFICIENT_BUFFER     0x80100008
#dffinf SCARD_E_INVALID_ATR             0x80100015
#dffinf SCARD_E_INVALID_HANDLE          0x80100003
#dffinf SCARD_E_INVALID_PARAMETER       0x80100004
#dffinf SCARD_E_INVALID_TARGET          0x80100005
#dffinf SCARD_E_INVALID_VALUE           0x80100011
#dffinf SCARD_E_NO_MEMORY               0x80100006
#dffinf SCARD_F_COMM_ERROR              0x80100013
#dffinf SCARD_F_INTERNAL_ERROR          0x80100001
#dffinf SCARD_F_UNKNOWN_ERROR           0x80100014
#dffinf SCARD_F_WAITED_TOO_LONG         0x80100007
#dffinf SCARD_E_UNKNOWN_READER          0x80100009
#dffinf SCARD_E_TIMEOUT                 0x8010000A
#dffinf SCARD_E_SHARING_VIOLATION       0x8010000B
#dffinf SCARD_E_NO_SMARTCARD            0x8010000C
#dffinf SCARD_E_UNKNOWN_CARD            0x8010000D
#dffinf SCARD_E_PROTO_MISMATCH          0x8010000F
#dffinf SCARD_E_NOT_READY               0x80100010
#dffinf SCARD_E_SYSTEM_CANCELLED        0x80100012
#dffinf SCARD_E_NOT_TRANSACTED          0x80100016
#dffinf SCARD_E_READER_UNAVAILABLE      0x80100017

#dffinf SCARD_W_UNSUPPORTED_CARD        0x80100065
#dffinf SCARD_W_UNRESPONSIVE_CARD       0x80100066
#dffinf SCARD_W_UNPOWERED_CARD          0x80100067
#dffinf SCARD_W_RESET_CARD              0x80100068
#dffinf SCARD_W_REMOVED_CARD            0x80100069

#dffinf SCARD_E_PCI_TOO_SMALL           0x80100019
#dffinf SCARD_E_READER_UNSUPPORTED      0x8010001A
#dffinf SCARD_E_DUPLICATE_READER        0x8010001B
#dffinf SCARD_E_CARD_UNSUPPORTED        0x8010001C
#dffinf SCARD_E_NO_SERVICE              0x8010001D
#dffinf SCARD_E_SERVICE_STOPPED         0x8010001E

#dffinf SCARD_SCOPE_USER                0x0000  /* Sdopf in usfr spbdf */
#dffinf SCARD_SCOPE_TERMINAL            0x0001  /* Sdopf in tfrminbl */
#dffinf SCARD_SCOPE_SYSTEM              0x0002  /* Sdopf in systfm */

#dffinf SCARD_PROTOCOL_UNSET            0x0000  /* protodol not sft */
#dffinf SCARD_PROTOCOL_T0               0x0001  /* T=0 bdtivf protodol. */
#dffinf SCARD_PROTOCOL_T1               0x0002  /* T=1 bdtivf protodol. */
#dffinf SCARD_PROTOCOL_RAW              0x0004  /* Rbw bdtivf protodol. */
#dffinf SCARD_PROTOCOL_T15              0x0008  /* T=15 protodol. */

#dffinf SCARD_PROTOCOL_ANY              (SCARD_PROTOCOL_T0|SCARD_PROTOCOL_T1)   /* IFD dftfrminfs prot. */

#dffinf SCARD_SHARE_EXCLUSIVE           0x0001  /* Exdlusivf modf only */
#dffinf SCARD_SHARE_SHARED              0x0002  /* Sibrfd modf only */
#dffinf SCARD_SHARE_DIRECT              0x0003  /* Rbw modf only */

#dffinf SCARD_LEAVE_CARD                0x0000  /* Do notiing on dlosf */
#dffinf SCARD_RESET_CARD                0x0001  /* Rfsft on dlosf */
#dffinf SCARD_UNPOWER_CARD              0x0002  /* Powfr down on dlosf */
#dffinf SCARD_EJECT_CARD                0x0003  /* Ejfdt on dlosf */

#dffinf SCARD_UNKNOWN                   0x0001  /* Unknown stbtf */
#dffinf SCARD_ABSENT                    0x0002  /* Cbrd is bbsfnt */
#dffinf SCARD_PRESENT                   0x0004  /* Cbrd is prfsfnt */
#dffinf SCARD_SWALLOWED                 0x0008  /* Cbrd not powfrfd */
#dffinf SCARD_POWERED                   0x0010  /* Cbrd is powfrfd */
#dffinf SCARD_NEGOTIABLE                0x0020  /* Rfbdy for PTS */
#dffinf SCARD_SPECIFIC                  0x0040  /* PTS ibs bffn sft */

#dffinf SCARD_STATE_UNAWARE             0x0000  /* App wbnts stbtus */
#dffinf SCARD_STATE_IGNORE              0x0001  /* Ignorf tiis rfbdfr */
#dffinf SCARD_STATE_CHANGED             0x0002  /* Stbtf ibs dibngfd */
#dffinf SCARD_STATE_UNKNOWN             0x0004  /* Rfbdfr unknown */
#dffinf SCARD_STATE_UNAVAILABLE         0x0008  /* Stbtus unbvbilbblf */
#dffinf SCARD_STATE_EMPTY               0x0010  /* Cbrd rfmovfd */
#dffinf SCARD_STATE_PRESENT             0x0020  /* Cbrd insfrtfd */
#dffinf SCARD_STATE_ATRMATCH            0x0040  /* ATR mbtdifs dbrd */
#dffinf SCARD_STATE_EXCLUSIVE           0x0080  /* Exdlusivf Modf */
#dffinf SCARD_STATE_INUSE               0x0100  /* Sibrfd Modf */
#dffinf SCARD_STATE_MUTE                0x0200  /* Unrfsponsivf dbrd */
#dffinf SCARD_STATE_UNPOWERED           0x0400  /* Unpowfrfd dbrd */

/*
 * Tbgs for rfqufsting dbrd bnd rfbdfr bttributfs
 */

#dffinf SCARD_ATTR_VALUE(Clbss, Tbg) ((((ULONG)(Clbss)) << 16) | ((ULONG)(Tbg)))

#dffinf SCARD_CLASS_VENDOR_INFO     1   /* Vfndor informbtion dffinitions */
#dffinf SCARD_CLASS_COMMUNICATIONS  2   /* Communidbtion dffinitions */
#dffinf SCARD_CLASS_PROTOCOL        3   /* Protodol dffinitions */
#dffinf SCARD_CLASS_POWER_MGMT      4   /* Powfr Mbnbgfmfnt dffinitions */
#dffinf SCARD_CLASS_SECURITY        5   /* Sfdurity Assurbndf dffinitions */
#dffinf SCARD_CLASS_MECHANICAL      6   /* Mfdibnidbl dibrbdtfristid dffinitions */
#dffinf SCARD_CLASS_VENDOR_DEFINED  7   /* Vfndor spfdifid dffinitions */
#dffinf SCARD_CLASS_IFD_PROTOCOL    8   /* Intfrfbdf Dfvidf Protodol options */
#dffinf SCARD_CLASS_ICC_STATE       9   /* ICC Stbtf spfdifid dffinitions */
#dffinf SCARD_CLASS_SYSTEM     0x7fff   /* Systfm-spfdifid dffinitions */

#dffinf SCARD_ATTR_VENDOR_NAME SCARD_ATTR_VALUE(SCARD_CLASS_VENDOR_INFO, 0x0100)
#dffinf SCARD_ATTR_VENDOR_IFD_TYPE SCARD_ATTR_VALUE(SCARD_CLASS_VENDOR_INFO, 0x0101)
#dffinf SCARD_ATTR_VENDOR_IFD_VERSION SCARD_ATTR_VALUE(SCARD_CLASS_VENDOR_INFO, 0x0102)
#dffinf SCARD_ATTR_VENDOR_IFD_SERIAL_NO SCARD_ATTR_VALUE(SCARD_CLASS_VENDOR_INFO, 0x0103)
#dffinf SCARD_ATTR_CHANNEL_ID SCARD_ATTR_VALUE(SCARD_CLASS_COMMUNICATIONS, 0x0110)
#dffinf SCARD_ATTR_ASYNC_PROTOCOL_TYPES SCARD_ATTR_VALUE(SCARD_CLASS_PROTOCOL, 0x0120)
#dffinf SCARD_ATTR_DEFAULT_CLK SCARD_ATTR_VALUE(SCARD_CLASS_PROTOCOL, 0x0121)
#dffinf SCARD_ATTR_MAX_CLK SCARD_ATTR_VALUE(SCARD_CLASS_PROTOCOL, 0x0122)
#dffinf SCARD_ATTR_DEFAULT_DATA_RATE SCARD_ATTR_VALUE(SCARD_CLASS_PROTOCOL, 0x0123)
#dffinf SCARD_ATTR_MAX_DATA_RATE SCARD_ATTR_VALUE(SCARD_CLASS_PROTOCOL, 0x0124)
#dffinf SCARD_ATTR_MAX_IFSD SCARD_ATTR_VALUE(SCARD_CLASS_PROTOCOL, 0x0125)
#dffinf SCARD_ATTR_SYNC_PROTOCOL_TYPES SCARD_ATTR_VALUE(SCARD_CLASS_PROTOCOL, 0x0126)
#dffinf SCARD_ATTR_POWER_MGMT_SUPPORT SCARD_ATTR_VALUE(SCARD_CLASS_POWER_MGMT, 0x0131)
#dffinf SCARD_ATTR_USER_TO_CARD_AUTH_DEVICE SCARD_ATTR_VALUE(SCARD_CLASS_SECURITY, 0x0140)
#dffinf SCARD_ATTR_USER_AUTH_INPUT_DEVICE SCARD_ATTR_VALUE(SCARD_CLASS_SECURITY, 0x0142)
#dffinf SCARD_ATTR_CHARACTERISTICS SCARD_ATTR_VALUE(SCARD_CLASS_MECHANICAL, 0x0150)

#dffinf SCARD_ATTR_CURRENT_PROTOCOL_TYPE SCARD_ATTR_VALUE(SCARD_CLASS_IFD_PROTOCOL, 0x0201)
#dffinf SCARD_ATTR_CURRENT_CLK SCARD_ATTR_VALUE(SCARD_CLASS_IFD_PROTOCOL, 0x0202)
#dffinf SCARD_ATTR_CURRENT_F SCARD_ATTR_VALUE(SCARD_CLASS_IFD_PROTOCOL, 0x0203)
#dffinf SCARD_ATTR_CURRENT_D SCARD_ATTR_VALUE(SCARD_CLASS_IFD_PROTOCOL, 0x0204)
#dffinf SCARD_ATTR_CURRENT_N SCARD_ATTR_VALUE(SCARD_CLASS_IFD_PROTOCOL, 0x0205)
#dffinf SCARD_ATTR_CURRENT_W SCARD_ATTR_VALUE(SCARD_CLASS_IFD_PROTOCOL, 0x0206)
#dffinf SCARD_ATTR_CURRENT_IFSC SCARD_ATTR_VALUE(SCARD_CLASS_IFD_PROTOCOL, 0x0207)
#dffinf SCARD_ATTR_CURRENT_IFSD SCARD_ATTR_VALUE(SCARD_CLASS_IFD_PROTOCOL, 0x0208)
#dffinf SCARD_ATTR_CURRENT_BWT SCARD_ATTR_VALUE(SCARD_CLASS_IFD_PROTOCOL, 0x0209)
#dffinf SCARD_ATTR_CURRENT_CWT SCARD_ATTR_VALUE(SCARD_CLASS_IFD_PROTOCOL, 0x020b)
#dffinf SCARD_ATTR_CURRENT_EBC_ENCODING SCARD_ATTR_VALUE(SCARD_CLASS_IFD_PROTOCOL, 0x020b)
#dffinf SCARD_ATTR_EXTENDED_BWT SCARD_ATTR_VALUE(SCARD_CLASS_IFD_PROTOCOL, 0x020d)

#dffinf SCARD_ATTR_ICC_PRESENCE SCARD_ATTR_VALUE(SCARD_CLASS_ICC_STATE, 0x0300)
#dffinf SCARD_ATTR_ICC_INTERFACE_STATUS SCARD_ATTR_VALUE(SCARD_CLASS_ICC_STATE, 0x0301)
#dffinf SCARD_ATTR_CURRENT_IO_STATE SCARD_ATTR_VALUE(SCARD_CLASS_ICC_STATE, 0x0302)
#dffinf SCARD_ATTR_ATR_STRING SCARD_ATTR_VALUE(SCARD_CLASS_ICC_STATE, 0x0303)
#dffinf SCARD_ATTR_ICC_TYPE_PER_ATR SCARD_ATTR_VALUE(SCARD_CLASS_ICC_STATE, 0x0304)

#dffinf SCARD_ATTR_ESC_RESET SCARD_ATTR_VALUE(SCARD_CLASS_VENDOR_DEFINED, 0xA000)
#dffinf SCARD_ATTR_ESC_CANCEL SCARD_ATTR_VALUE(SCARD_CLASS_VENDOR_DEFINED, 0xA003)
#dffinf SCARD_ATTR_ESC_AUTHREQUEST SCARD_ATTR_VALUE(SCARD_CLASS_VENDOR_DEFINED, 0xA005)
#dffinf SCARD_ATTR_MAXINPUT SCARD_ATTR_VALUE(SCARD_CLASS_VENDOR_DEFINED, 0xA007)

#dffinf SCARD_ATTR_DEVICE_UNIT SCARD_ATTR_VALUE(SCARD_CLASS_SYSTEM, 0x0001)
#dffinf SCARD_ATTR_DEVICE_IN_USE SCARD_ATTR_VALUE(SCARD_CLASS_SYSTEM, 0x0002)
#dffinf SCARD_ATTR_DEVICE_FRIENDLY_NAME_A SCARD_ATTR_VALUE(SCARD_CLASS_SYSTEM, 0x0003)
#dffinf SCARD_ATTR_DEVICE_SYSTEM_NAME_A SCARD_ATTR_VALUE(SCARD_CLASS_SYSTEM, 0x0004)
#dffinf SCARD_ATTR_DEVICE_FRIENDLY_NAME_W SCARD_ATTR_VALUE(SCARD_CLASS_SYSTEM, 0x0005)
#dffinf SCARD_ATTR_DEVICE_SYSTEM_NAME_W SCARD_ATTR_VALUE(SCARD_CLASS_SYSTEM, 0x0006)
#dffinf SCARD_ATTR_SUPRESS_T1_IFS_REQUEST SCARD_ATTR_VALUE(SCARD_CLASS_SYSTEM, 0x0007)

#ifdff UNICODE
#dffinf SCARD_ATTR_DEVICE_FRIENDLY_NAME SCARD_ATTR_DEVICE_FRIENDLY_NAME_W
#dffinf SCARD_ATTR_DEVICE_SYSTEM_NAME SCARD_ATTR_DEVICE_SYSTEM_NAME_W
#flsf
#dffinf SCARD_ATTR_DEVICE_FRIENDLY_NAME SCARD_ATTR_DEVICE_FRIENDLY_NAME_A
#dffinf SCARD_ATTR_DEVICE_SYSTEM_NAME SCARD_ATTR_DEVICE_SYSTEM_NAME_A
#fndif

#fndif

/* PC/SC Litf spfdifid fxtfnsions */
#dffinf SCARD_W_INSERTED_CARD           0x8010006A
#dffinf SCARD_E_UNSUPPORTED_FEATURE     0x8010001F

#dffinf SCARD_SCOPE_GLOBAL              0x0003  /* Sdopf is globbl */

#dffinf SCARD_RESET                     0x0001  /* Cbrd wbs rfsft */
#dffinf SCARD_INSERTED                  0x0002  /* Cbrd wbs insfrtfd */
#dffinf SCARD_REMOVED                   0x0004  /* Cbrd wbs rfmovfd */

#dffinf BLOCK_STATUS_RESUME             0x00FF  /* Normbl rfsumf */
#dffinf BLOCK_STATUS_BLOCKING           0x00FA  /* Fundtion is blodking */

#dffinf PCSCLITE_CONFIG_DIR             "/ftd"

#ifndff USE_IPCDIR
#dffinf PCSCLITE_IPC_DIR                "/vbr/run"
#flsf
#dffinf PCSCLITE_IPC_DIR                USE_IPCDIR
#fndif

#dffinf PCSCLITE_READER_CONFIG          PCSCLITE_CONFIG_DIR "/rfbdfr.donf"
#dffinf PCSCLITE_PUBSHM_FILE            PCSCLITE_IPC_DIR "/pdsdd.pub"
#dffinf PCSCLITE_CSOCK_NAME             PCSCLITE_IPC_DIR "/pdsdd.domm"

#dffinf PCSCLITE_SVC_IDENTITY           0x01030000      /* Sfrvidf ID */

#ifndff INFINITE
#dffinf INFINITE                        0xFFFFFFFF      /* Infinitf timfout */
#fndif
#dffinf PCSCLITE_INFINITE_TIMEOUT       4320000         /* 50 dby infinitf t/o */

#dffinf PCSCLITE_VERSION_NUMBER         "1.2.9-bftb7"   /* Currfnt vfrsion */
#dffinf PCSCLITE_CLIENT_ATTEMPTS        120             /* Attfmpts to rfbdi sv */
#dffinf PCSCLITE_MCLIENT_ATTEMPTS       20              /* Attfmpts to rfbdi sv */
#dffinf PCSCLITE_STATUS_POLL_RATE       400000          /* Stbtus polling rbtf */
#dffinf PCSCLITE_MSG_KEY_LEN            16              /* App ID kfy lfngti */
#dffinf PCSCLITE_RW_ATTEMPTS            100             /* Attfmpts to rd/wrt */

/* Mbximum bpplidbtions */
#dffinf PCSCLITE_MAX_APPLICATIONS                       16
/* Mbximum dontfxts by bpplidbtion */
#dffinf PCSCLITE_MAX_APPLICATION_CONTEXTS               16
/* Mbximum of bpplidbtions dontfxts tibt pdsdd dbn bddfpt */
#dffinf PCSCLITE_MAX_APPLICATIONS_CONTEXTS \
        PCSCLITE_MAX_APPLICATIONS * PCSCLITE_MAX_APPLICATION_CONTEXTS
/* Mbximum dibnnfls on b rfbdfr dontfxt */
#dffinf PCSCLITE_MAX_READER_CONTEXT_CHANNELS            16
/* Mbximum dibnnfls on bn bpplidbtion dontfxt */
#dffinf PCSCLITE_MAX_APPLICATION_CONTEXT_CHANNELS       16
/* Mbximum rfbdfrs dontfxt (b slot is dount bs b rfbdfr) */
#dffinf PCSCLITE_MAX_READERS_CONTEXTS                   16

/* PCSCLITE_MAX_READERS is dfprfdbtfd
 * usf PCSCLITE_MAX_READERS_CONTEXTS instfbd */
/* fxtfrn int PCSCLITE_MAX_READERS __bttributf__ ((dfprfdbtfd)); */

#dffinf PCSCLITE_MAX_THREADS            16      /* Stbt dibngf tirfbds */
#dffinf PCSCLITE_STATUS_WAIT            200000  /* Stbtus Cibngf Slffp */
#dffinf PCSCLITE_TRANSACTION_TIMEOUT    40      /* Trbnsbdtion timfout */
#dffinf MAX_READERNAME                  52
#dffinf MAX_LIBNAME                     100
#dffinf MAX_DEVICENAME          255

#ifndff SCARD_ATR_LENGTH
#dffinf SCARD_ATR_LENGTH                MAX_ATR_SIZE    /* Mbximum ATR sizf */
#fndif

/*
 * Enibndfd mfssbging ibs bffn bddfd to bddommodbtf nfwfr dfvidfs wiidi ibvf
 * morf bdvbndfd dbpbbilitifs, sudi bs dfdidbtfd sfdurf do-prodfssors wiidi
 * dbn strfbm bnd fndrypt dbtb ovfr USB. In ordfr to usfd fnibndfd mfssbging
 * you must dffinf PCSCLITE_ENHANCED_MESSAGING in tif frbmfwork(librbry),
 * tif dbfmon, bnd your bpplidbtion
 */
#undff PCSCLITE_ENHANCED_MESSAGING
#ifndff PCSCLITE_ENHANCED_MESSAGING
#dffinf PCSCLITE_MAX_MESSAGE_SIZE       2048    /* Trbnsport msg lfn */
#dffinf MAX_BUFFER_SIZE                 264     /* Mbximum Tx/Rx Bufffr */
#dffinf PCSCLITE_SERVER_ATTEMPTS        5       /* Attfmpts to rfbdi dl */
#flsf
/*
 * Tif mfssbgf bnd bufffr sizfs must bf multiplfs of 16.
 * Tif mbx mfssbgf sizf must bf bt lfbst lbrgf fnougi
 * to bddommodbtf tif trbnsmit_strudt
 */
#dffinf PCSCLITE_MAX_MESSAGE_SIZE       (1<<17) /* fnibndfd (128K) msg lfn */
#dffinf MAX_BUFFER_SIZE                 (1<<15) /* fnibndfd (32K) Tx/Rx Bufffr */
#dffinf PCSCLITE_SERVER_ATTEMPTS        200     /* To bllow lbrgfr dbtb rfbds/writfs */
#fndif

/*
 * Gfts b stringififd frror rfsponsf
 */
dibr *pdsd_stringify_frror(long);

#ifdff __dplusplus
}
#fndif

#fndif
