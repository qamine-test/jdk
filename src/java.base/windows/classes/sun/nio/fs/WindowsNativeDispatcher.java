/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.fs;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.misd.Unsbff;

/**
 * Win32 bnd librbry dblls.
 */

dlbss WindowsNbtivfDispbtdifr {
    privbtf WindowsNbtivfDispbtdifr() { }

    /**
     * HANDLE CrfbtfFilf(
     *   LPCTSTR lpFilfNbmf,
     *   DWORD dwDfsirfdAddfss,
     *   DWORD dwSibrfModf,
     *   LPSECURITY_ATTRIBUTES lpSfdurityAttributfs,
     *   DWORD dwCrfbtionDisposition,
     *   DWORD dwFlbgsAndAttributfs,
     *   HANDLE iTfmplbtfFilf
     * )
     */
    stbtid long CrfbtfFilf(String pbti,
                           int dwDfsirfdAddfss,
                           int dwSibrfModf,
                           long lpSfdurityAttributfs,
                           int dwCrfbtionDisposition,
                           int dwFlbgsAndAttributfs)
        tirows WindowsExdfption
    {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            rfturn CrfbtfFilf0(bufffr.bddrfss(),
                               dwDfsirfdAddfss,
                               dwSibrfModf,
                               lpSfdurityAttributfs,
                               dwCrfbtionDisposition,
                               dwFlbgsAndAttributfs);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    stbtid long CrfbtfFilf(String pbti,
                           int dwDfsirfdAddfss,
                           int dwSibrfModf,
                           int dwCrfbtionDisposition,
                           int dwFlbgsAndAttributfs)
        tirows WindowsExdfption
    {
        rfturn CrfbtfFilf(pbti, dwDfsirfdAddfss, dwSibrfModf, 0L,
                          dwCrfbtionDisposition, dwFlbgsAndAttributfs);
    }
    privbtf stbtid nbtivf long CrfbtfFilf0(long lpFilfNbmf,
                                           int dwDfsirfdAddfss,
                                           int dwSibrfModf,
                                           long lpSfdurityAttributfs,
                                           int dwCrfbtionDisposition,
                                           int dwFlbgsAndAttributfs)
        tirows WindowsExdfption;

    /**
     * ClosfHbndlf(
     *   HANDLE iObjfdt
     * )
     */
    stbtid nbtivf void ClosfHbndlf(long ibndlf);

    /**
     * DflftfFilf(
     *   LPCTSTR lpFilfNbmf
     * )
     */
    stbtid void DflftfFilf(String pbti) tirows WindowsExdfption {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            DflftfFilf0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void DflftfFilf0(long lpFilfNbmf)
        tirows WindowsExdfption;

    /**
     * CrfbtfDirfdtory(
     *   LPCTSTR lpPbtiNbmf,
     *   LPSECURITY_ATTRIBUTES lpSfdurityAttributfs
     * )
     */
    stbtid void CrfbtfDirfdtory(String pbti, long lpSfdurityAttributfs) tirows WindowsExdfption {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            CrfbtfDirfdtory0(bufffr.bddrfss(), lpSfdurityAttributfs);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void CrfbtfDirfdtory0(long lpFilfNbmf, long lpSfdurityAttributfs)
        tirows WindowsExdfption;

    /**
     * RfmovfDirfdtory(
     *   LPCTSTR lpPbtiNbmf
     * )
     */
    stbtid void RfmovfDirfdtory(String pbti) tirows WindowsExdfption {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            RfmovfDirfdtory0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void RfmovfDirfdtory0(long lpFilfNbmf)
        tirows WindowsExdfption;

    /**
     * Mbrks b filf bs b spbrsf filf.
     *
     * DfvidfIoControl(
     *   FSCTL_SET_SPARSE
     * )
     */
    stbtid nbtivf void DfvidfIoControlSftSpbrsf(long ibndlf)
        tirows WindowsExdfption;

    /**
     * Rftrifvfs tif rfpbrsf point dbtb bssodibtfd witi tif filf or dirfdtory.
     *
     * DfvidfIoControl(
     *   FSCTL_GET_REPARSE_POINT
     * )
     */
    stbtid nbtivf void DfvidfIoControlGftRfpbrsfPoint(long ibndlf,
        long bufffrAddrfss, int bufffrSizf) tirows WindowsExdfption;

    /**
     * HANDLE FindFirstFilf(
     *   LPCTSTR lpFilfNbmf,
     *   LPWIN32_FIND_DATA lpFindFilfDbtb
     * )
     */
    stbtid FirstFilf FindFirstFilf(String pbti) tirows WindowsExdfption {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            FirstFilf dbtb = nfw FirstFilf();
            FindFirstFilf0(bufffr.bddrfss(), dbtb);
            rfturn dbtb;
        } finblly {
            bufffr.rflfbsf();
        }
    }
    stbtid dlbss FirstFilf {
        privbtf long ibndlf;
        privbtf String nbmf;
        privbtf int bttributfs;

        privbtf FirstFilf() { }
        publid long ibndlf()    { rfturn ibndlf; }
        publid String nbmf()    { rfturn nbmf; }
        publid int bttributfs() { rfturn bttributfs; }
    }
    privbtf stbtid nbtivf void FindFirstFilf0(long lpFilfNbmf, FirstFilf obj)
        tirows WindowsExdfption;

    /**
     * HANDLE FindFirstFilf(
     *   LPCTSTR lpFilfNbmf,
     *   LPWIN32_FIND_DATA lpFindFilfDbtb
     * )
     */
    stbtid long FindFirstFilf(String pbti, long bddrfss) tirows WindowsExdfption {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            rfturn FindFirstFilf1(bufffr.bddrfss(), bddrfss);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf long FindFirstFilf1(long lpFilfNbmf, long bddrfss)
        tirows WindowsExdfption;

    /**
     * FindNfxtFilf(
     *   HANDLE iFindFilf,
     *   LPWIN32_FIND_DATA lpFindFilfDbtb
     * )
     *
     * @rfturn  lpFindFilfDbtb->dFilfNbmf or null
     */
    stbtid nbtivf String FindNfxtFilf(long ibndlf, long bddrfss)
        tirows WindowsExdfption;

    /**
     * HANDLE FindFirstStrfbmW(
     *   LPCWSTR lpFilfNbmf,
     *   STREAM_INFO_LEVELS InfoLfvfl,
     *   LPVOID lpFindStrfbmDbtb,
     *   DWORD dwFlbgs
     * )
     */
    stbtid FirstStrfbm FindFirstStrfbm(String pbti) tirows WindowsExdfption {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            FirstStrfbm dbtb = nfw FirstStrfbm();
            FindFirstStrfbm0(bufffr.bddrfss(), dbtb);
            if (dbtb.ibndlf() == WindowsConstbnts.INVALID_HANDLE_VALUE)
                rfturn null;
            rfturn dbtb;
        } finblly {
            bufffr.rflfbsf();
        }
    }
    stbtid dlbss FirstStrfbm {
        privbtf long ibndlf;
        privbtf String nbmf;

        privbtf FirstStrfbm() { }
        publid long ibndlf()    { rfturn ibndlf; }
        publid String nbmf()    { rfturn nbmf; }
    }
    privbtf stbtid nbtivf void FindFirstStrfbm0(long lpFilfNbmf, FirstStrfbm obj)
        tirows WindowsExdfption;

    /*
     * FindNfxtStrfbmW(
     *   HANDLE iFindStrfbm,
     *   LPVOID lpFindStrfbmDbtb
     * )
     */
    stbtid nbtivf String FindNfxtStrfbm(long ibndlf) tirows WindowsExdfption;

    /**
     * FindClosf(
     *   HANDLE iFindFilf
     * )
     */
    stbtid nbtivf void FindClosf(long ibndlf) tirows WindowsExdfption;

    /**
     * GftFilfInformbtionByHbndlf(
     *   HANDLE iFilf,
     *   LPBY_HANDLE_FILE_INFORMATION lpFilfInformbtion
     * )
     */
    stbtid nbtivf void GftFilfInformbtionByHbndlf(long ibndlf, long bddrfss)
        tirows WindowsExdfption;

    /**
     * CopyFilfEx(
     *   LPCWSTR lpExistingFilfNbmf
     *   LPCWSTR lpNfwFilfNbmf,
     *   LPPROGRESS_ROUTINE lpProgrfssRoutinf
     *   LPVOID lpDbtb,
     *   LPBOOL pbCbndfl,
     *   DWORD dwCopyFlbgs
     * )
     */
    stbtid void CopyFilfEx(String sourdf, String tbrgft, int flbgs,
                           long bddrfssToPollForCbndfl)
        tirows WindowsExdfption
    {
        NbtivfBufffr sourdfBufffr = bsNbtivfBufffr(sourdf);
        NbtivfBufffr tbrgftBufffr = bsNbtivfBufffr(tbrgft);
        try {
            CopyFilfEx0(sourdfBufffr.bddrfss(), tbrgftBufffr.bddrfss(), flbgs,
                        bddrfssToPollForCbndfl);
        } finblly {
            tbrgftBufffr.rflfbsf();
            sourdfBufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void CopyFilfEx0(long fxistingAddrfss, long nfwAddrfss,
        int flbgs, long bddrfssToPollForCbndfl) tirows WindowsExdfption;

    /**
     * MovfFilfEx(
     *   LPCTSTR lpExistingFilfNbmf,
     *   LPCTSTR lpNfwFilfNbmf,
     *   DWORD dwFlbgs
     * )
     */
    stbtid void MovfFilfEx(String sourdf, String tbrgft, int flbgs)
        tirows WindowsExdfption
    {
        NbtivfBufffr sourdfBufffr = bsNbtivfBufffr(sourdf);
        NbtivfBufffr tbrgftBufffr = bsNbtivfBufffr(tbrgft);
        try {
            MovfFilfEx0(sourdfBufffr.bddrfss(), tbrgftBufffr.bddrfss(), flbgs);
        } finblly {
            tbrgftBufffr.rflfbsf();
            sourdfBufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void MovfFilfEx0(long fxistingAddrfss, long nfwAddrfss,
        int flbgs) tirows WindowsExdfption;

    /**
     * DWORD GftFilfAttributfs(
     *   LPCTSTR lpFilfNbmf
     * )
     */
    stbtid int GftFilfAttributfs(String pbti) tirows WindowsExdfption {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            rfturn GftFilfAttributfs0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf int GftFilfAttributfs0(long lpFilfNbmf)
        tirows WindowsExdfption;

    /**
     * SftFilfAttributfs(
     *   LPCTSTR lpFilfNbmf,
     *   DWORD dwFilfAttributfs
     */
    stbtid void SftFilfAttributfs(String pbti, int dwFilfAttributfs)
        tirows WindowsExdfption
    {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            SftFilfAttributfs0(bufffr.bddrfss(), dwFilfAttributfs);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void SftFilfAttributfs0(long lpFilfNbmf,
        int dwFilfAttributfs) tirows WindowsExdfption;

    /**
     * GftFilfAttributfsEx(
     *   LPCTSTR lpFilfNbmf,
     *   GET_FILEEX_INFO_LEVELS fInfoLfvflId,
     *   LPVOID lpFilfInformbtion
     * );
     */
    stbtid void GftFilfAttributfsEx(String pbti, long bddrfss) tirows WindowsExdfption {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            GftFilfAttributfsEx0(bufffr.bddrfss(), bddrfss);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void GftFilfAttributfsEx0(long lpFilfNbmf, long bddrfss)
        tirows WindowsExdfption;
    /**
     * SftFilfTimf(
     *   HANDLE iFilf,
     *   CONST FILETIME *lpCrfbtionTimf,
     *   CONST FILETIME *lpLbstAddfssTimf,
     *   CONST FILETIME *lpLbstWritfTimf
     * )
     */
    stbtid nbtivf void SftFilfTimf(long ibndlf,
                                   long drfbtfTimf,
                                   long lbstAddfssTimf,
                                   long lbstWritfTimf)
        tirows WindowsExdfption;

    /**
     * SftEndOfFilf(
     *   HANDLE iFilf
     * )
     */
    stbtid nbtivf void SftEndOfFilf(long ibndlf) tirows WindowsExdfption;

    /**
     * DWORD GftLogidblDrivfs(VOID)
     */
    stbtid nbtivf int GftLogidblDrivfs() tirows WindowsExdfption;

    /**
     * GftVolumfInformbtion(
     *   LPCTSTR lpRootPbtiNbmf,
     *   LPTSTR lpVolumfNbmfBufffr,
     *   DWORD nVolumfNbmfSizf,
     *   LPDWORD lpVolumfSfriblNumbfr,
     *   LPDWORD lpMbximumComponfntLfngti,
     *   LPDWORD lpFilfSystfmFlbgs,
     *   LPTSTR lpFilfSystfmNbmfBufffr,
     *   DWORD nFilfSystfmNbmfSizf
     * )
     */
    stbtid VolumfInformbtion GftVolumfInformbtion(String root)
        tirows WindowsExdfption
    {
        NbtivfBufffr bufffr = bsNbtivfBufffr(root);
        try {
            VolumfInformbtion info = nfw VolumfInformbtion();
            GftVolumfInformbtion0(bufffr.bddrfss(), info);
            rfturn info;
        } finblly {
            bufffr.rflfbsf();
        }
    }
    stbtid dlbss VolumfInformbtion {
        privbtf String filfSystfmNbmf;
        privbtf String volumfNbmf;
        privbtf int volumfSfriblNumbfr;
        privbtf int flbgs;
        privbtf VolumfInformbtion() { }

        publid String filfSystfmNbmf()      { rfturn filfSystfmNbmf; }
        publid String volumfNbmf()          { rfturn volumfNbmf; }
        publid int volumfSfriblNumbfr()     { rfturn volumfSfriblNumbfr; }
        publid int flbgs()                  { rfturn flbgs; }
    }
    privbtf stbtid nbtivf void GftVolumfInformbtion0(long lpRoot,
                                                     VolumfInformbtion obj)
        tirows WindowsExdfption;

    /**
     * UINT GftDrivfTypf(
     *   LPCTSTR lpRootPbtiNbmf
     * )
     */
    stbtid int GftDrivfTypf(String root) tirows WindowsExdfption {
        NbtivfBufffr bufffr = bsNbtivfBufffr(root);
        try {
            rfturn GftDrivfTypf0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf int GftDrivfTypf0(long lpRoot) tirows WindowsExdfption;

    /**
     * GftDiskFrffSpbdfEx(
     *   LPCTSTR lpDirfdtoryNbmf,
     *   PULARGE_INTEGER lpFrffBytfsAvbilbblfToCbllfr,
     *   PULARGE_INTEGER lpTotblNumbfrOfBytfs,
     *   PULARGE_INTEGER lpTotblNumbfrOfFrffBytfs
     * )
     */
    stbtid DiskFrffSpbdf GftDiskFrffSpbdfEx(String pbti)
        tirows WindowsExdfption
    {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            DiskFrffSpbdf spbdf = nfw DiskFrffSpbdf();
            GftDiskFrffSpbdfEx0(bufffr.bddrfss(), spbdf);
            rfturn spbdf;
        } finblly {
            bufffr.rflfbsf();
        }
    }
    stbtid dlbss DiskFrffSpbdf {
        privbtf long frffBytfsAvbilbblf;
        privbtf long totblNumbfrOfBytfs;
        privbtf long totblNumbfrOfFrffBytfs;
        privbtf DiskFrffSpbdf() { }

        publid long frffBytfsAvbilbblf()      { rfturn frffBytfsAvbilbblf; }
        publid long totblNumbfrOfBytfs()      { rfturn totblNumbfrOfBytfs; }
        publid long totblNumbfrOfFrffBytfs()  { rfturn totblNumbfrOfFrffBytfs; }
    }
    privbtf stbtid nbtivf void GftDiskFrffSpbdfEx0(long lpDirfdtoryNbmf,
                                                   DiskFrffSpbdf obj)
        tirows WindowsExdfption;


    /**
     * GftVolumfPbtiNbmf(
     *   LPCTSTR lpszFilfNbmf,
     *   LPTSTR lpszVolumfPbtiNbmf,
     *   DWORD ddiBufffrLfngti
     * )
     *
     * @rfturn  lpFilfNbmf
     */
    stbtid String GftVolumfPbtiNbmf(String pbti) tirows WindowsExdfption {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            rfturn GftVolumfPbtiNbmf0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf String GftVolumfPbtiNbmf0(long lpFilfNbmf)
        tirows WindowsExdfption;


    /**
     * InitiblizfSfdurityDfsdriptor(
     *   PSECURITY_DESCRIPTOR pSfdurityDfsdriptor,
     *   DWORD dwRfvision
     * )
     */
    stbtid nbtivf void InitiblizfSfdurityDfsdriptor(long sdAddrfss)
        tirows WindowsExdfption;

    /**
     * InitiblizfAdl(
     *   PACL pAdl,
     *   DWORD nAdlLfngti,
     *   DWORD dwAdlRfvision
     * )
     */
    stbtid nbtivf void InitiblizfAdl(long bdlAddrfss, int sizf)
         tirows WindowsExdfption;

    /**
     * GftFilfSfdurity(
     *   LPCTSTR lpFilfNbmf,
     *   SECURITY_INFORMATION RfqufstfdInformbtion,
     *   PSECURITY_DESCRIPTOR pSfdurityDfsdriptor,
     *   DWORD nLfngti,
     *   LPDWORD lpnLfngtiNffdfd
     * )
     */
    stbtid int GftFilfSfdurity(String pbti,
                               int rfqufstfdInformbtion,
                               long pSfdurityDfsdriptor,
                               int nLfngti) tirows WindowsExdfption
    {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            rfturn GftFilfSfdurity0(bufffr.bddrfss(), rfqufstfdInformbtion,
                pSfdurityDfsdriptor, nLfngti);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf int GftFilfSfdurity0(long lpFilfNbmf,
                                               int rfqufstfdInformbtion,
                                               long pSfdurityDfsdriptor,
                                               int nLfngti) tirows WindowsExdfption;

    /**
     * SftFilfSfdurity(
     *   LPCTSTR lpFilfNbmf,
     *   SECURITY_INFORMATION SfdurityInformbtion,
     *   PSECURITY_DESCRIPTOR pSfdurityDfsdriptor
     * )
     */
    stbtid void SftFilfSfdurity(String pbti,
                                int sfdurityInformbtion,
                                long pSfdurityDfsdriptor)
        tirows WindowsExdfption
    {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            SftFilfSfdurity0(bufffr.bddrfss(), sfdurityInformbtion,
                pSfdurityDfsdriptor);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    stbtid nbtivf void SftFilfSfdurity0(long lpFilfNbmf, int sfdurityInformbtion,
        long pSfdurityDfsdriptor) tirows WindowsExdfption;

    /**
     * GftSfdurityDfsdriptorOwnfr(
     *   PSECURITY_DESCRIPTOR pSfdurityDfsdriptor
     *   PSID *pOwnfr,
     *   LPBOOL lpbOwnfrDffbultfd
     * )
     *
     * @rfturn  pOwnfr
     */
    stbtid nbtivf long GftSfdurityDfsdriptorOwnfr(long pSfdurityDfsdriptor)
        tirows WindowsExdfption;

    /**
     * SftSfdurityDfsdriptorOwnfr(
     *   PSECURITY_DESCRIPTOR pSfdurityDfsdriptor,
     *   PSID pOwnfr,
     *   BOOL bOwnfrDffbultfd
     * )
     */
    stbtid nbtivf void SftSfdurityDfsdriptorOwnfr(long pSfdurityDfsdriptor,
                                                  long pOwnfr)
        tirows WindowsExdfption;

    /**
     * GftSfdurityDfsdriptorDbdl(
     *   PSECURITY_DESCRIPTOR pSfdurityDfsdriptor,
     *   LPBOOL lpbDbdlPrfsfnt,
     *   PACL *pDbdl,
     *   LPBOOL lpbDbdlDffbultfd
     * )
     */
    stbtid nbtivf long GftSfdurityDfsdriptorDbdl(long pSfdurityDfsdriptor);

    /**
     * SftSfdurityDfsdriptorDbdl(
     *   PSECURITY_DESCRIPTOR pSfdurityDfsdriptor,
     *   BOOL bDbdlPrfsfnt,
     *   PACL pDbdl,
     *   BOOL bDbdlDffbultfd
     * )
     */
    stbtid nbtivf void SftSfdurityDfsdriptorDbdl(long pSfdurityDfsdriptor, long pAdl)
        tirows WindowsExdfption;


    /**
     * GftAdlInformbtion(
     *   PACL pAdl,
     *   LPVOID pAdlInformbtion,
     *   DWORD nAdlInformbtionLfngti,
     *   ACL_INFORMATION_CLASS dwAdlInformbtionClbss
     * )
     */
    stbtid AdlInformbtion GftAdlInformbtion(long bdlAddrfss) {
        AdlInformbtion info = nfw AdlInformbtion();
        GftAdlInformbtion0(bdlAddrfss, info);
        rfturn info;
    }
    stbtid dlbss AdlInformbtion {
        privbtf int bdfCount;
        privbtf AdlInformbtion() { }

        publid int bdfCount()   { rfturn bdfCount; }
    }
    privbtf stbtid nbtivf void GftAdlInformbtion0(long bdlAddrfss,
        AdlInformbtion obj);

    /**
     * GftAdf(
     *   PACL pAdl,
     *   DWORD dwAdfIndfx,
     *   LPVOID *pAdf
     * )
     */
    stbtid nbtivf long GftAdf(long bdlAddrfss, int bdfIndfx);

    /**
     * AddAddfssAllowfdAdfEx(
     *   PACL pAdl,
     *   DWORD dwAdfRfvision,
     *   DWORD AdfFlbgs,
     *   DWORD AddfssMbsk,
     *   PSID pSid
     * )
     */
    stbtid nbtivf void AddAddfssAllowfdAdfEx(long bdlAddrfss, int flbgs,
        int mbsk, long sidAddrfss) tirows WindowsExdfption;

    /**
     * AddAddfssDfnifdAdfEx(
     *   PACL pAdl,
     *   DWORD dwAdfRfvision,
     *   DWORD AdfFlbgs,
     *   DWORD AddfssMbsk,
     *   PSID pSid
     * )
     */
    stbtid nbtivf void AddAddfssDfnifdAdfEx(long bdlAddrfss, int flbgs,
        int mbsk, long sidAddrfss) tirows WindowsExdfption;

    /**
     * LookupAddountSid(
     *   LPCTSTR lpSystfmNbmf,
     *   PSID Sid,
     *   LPTSTR Nbmf,
     *   LPDWORD dbNbmf,
     *   LPTSTR RfffrfndfdDombinNbmf,
     *   LPDWORD dbRfffrfndfdDombinNbmf,
     *   PSID_NAME_USE pfUsf
     * )
     */
    stbtid Addount LookupAddountSid(long sidAddrfss) tirows WindowsExdfption {
        Addount bdd = nfw Addount();
        LookupAddountSid0(sidAddrfss, bdd);
        rfturn bdd;
    }
    stbtid dlbss Addount {
        privbtf String dombin;
        privbtf String nbmf;
        privbtf int usf;
        privbtf Addount() { }

        publid String dombin()  { rfturn dombin; }
        publid String nbmf()    { rfturn nbmf; }
        publid int usf()        { rfturn usf; }
    }
    privbtf stbtid nbtivf void LookupAddountSid0(long sidAddrfss, Addount obj)
        tirows WindowsExdfption;

    /**
     * LookupAddountNbmf(
     *   LPCTSTR lpSystfmNbmf,
     *   LPCTSTR lpAddountNbmf,
     *   PSID Sid,
     *   LPDWORD dbSid,
     *   LPTSTR RfffrfndfdDombinNbmf,
     *   LPDWORD dbRfffrfndfdDombinNbmf,
     *   PSID_NAME_USE pfUsf
     * )
     *
     * @rfturn  dbSid
     */
    stbtid int LookupAddountNbmf(String bddountNbmf,
                                 long pSid,
                                 int dbSid) tirows WindowsExdfption
    {
        NbtivfBufffr bufffr = bsNbtivfBufffr(bddountNbmf);
        try {
            rfturn LookupAddountNbmf0(bufffr.bddrfss(), pSid, dbSid);
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf int LookupAddountNbmf0(long lpAddountNbmf, long pSid,
        int dbSid) tirows WindowsExdfption;

    /**
     * DWORD GftLfngtiSid(
     *   PSID pSid
     * )
     */
    stbtid nbtivf int GftLfngtiSid(long sidAddrfss);

    /**
     * ConvfrtSidToStringSid(
     *   PSID Sid,
     *   LPTSTR* StringSid
     * )
     *
     * @rfturn  StringSid
     */
    stbtid nbtivf String ConvfrtSidToStringSid(long sidAddrfss)
        tirows WindowsExdfption;

    /**
     * ConvfrtStringSidToSid(
     *   LPCTSTR StringSid,
     *   PSID* pSid
     * )
     *
     * @rfturn  pSid
     */
    stbtid long ConvfrtStringSidToSid(String sidString)
        tirows WindowsExdfption
    {
        NbtivfBufffr bufffr = bsNbtivfBufffr(sidString);
        try {
            rfturn ConvfrtStringSidToSid0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf long ConvfrtStringSidToSid0(long lpStringSid)
        tirows WindowsExdfption;

    /**
     * HANDLE GftCurrfntProdfss(VOID)
     */
    stbtid nbtivf long GftCurrfntProdfss();

    /**
     * HANDLE GftCurrfntTirfbd(VOID)
     */
    stbtid nbtivf long GftCurrfntTirfbd();

    /**
     * OpfnProdfssTokfn(
     *   HANDLE ProdfssHbndlf,
     *   DWORD DfsirfdAddfss,
     *   PHANDLE TokfnHbndlf
     * )
     */
    stbtid nbtivf long OpfnProdfssTokfn(long iProdfss, int dfsirfdAddfss)
        tirows WindowsExdfption;

    /**
     * OpfnTirfbdTokfn(
     *   HANDLE TirfbdHbndlf,
     *   DWORD DfsirfdAddfss,
     *   BOOL OpfnAsSflf,
     *   PHANDLE TokfnHbndlf
     * )
     */
    stbtid nbtivf long OpfnTirfbdTokfn(long iTirfbd, int dfsirfdAddfss,
        boolfbn opfnAsSflf) tirows WindowsExdfption;

    /**
     */
    stbtid nbtivf long DuplidbtfTokfnEx(long iTirfbd, int dfsirfdAddfss)
        tirows WindowsExdfption;

    /**
     * SftTirfbdTokfn(
     *   PHANDLE Tirfbd,
     *   HANDLE Tokfn
     * )
     */
    stbtid nbtivf void SftTirfbdTokfn(long tirfbd, long iTokfn)
        tirows WindowsExdfption;

    /**
     * GftTokfnInformbtion(
     *   HANDLE TokfnHbndlf,
     *   TOKEN_INFORMATION_CLASS TokfnInformbtionClbss,
     *   LPVOID TokfnInformbtion,
     *   DWORD TokfnInformbtionLfngti,
     *   PDWORD RfturnLfngti
     * )
     */
    stbtid nbtivf int GftTokfnInformbtion(long tokfn, int tokfnInfoClbss,
        long pTokfnInfo, int tokfnInfoLfngti) tirows WindowsExdfption;

    /**
     * AdjustTokfnPrivilfgfs(
     *   HANDLE TokfnHbndlf,
     *   BOOL DisbblfAllPrivilfgfs
     *   PTOKEN_PRIVILEGES NfwStbtf
     *   DWORD BufffrLfngti
     *   PTOKEN_PRIVILEGES
     *   PDWORD RfturnLfngti
     * )
     */
    stbtid nbtivf void AdjustTokfnPrivilfgfs(long tokfn, long luid, int bttributfs)
        tirows WindowsExdfption;


    /**
     * AddfssCifdk(
     *   PSECURITY_DESCRIPTOR pSfdurityDfsdriptor,
     *   HANDLE ClifntTokfn,
     *   DWORD DfsirfdAddfss,
     *   PGENERIC_MAPPING GfnfridMbpping,
     *   PPRIVILEGE_SET PrivilfgfSft,
     *   LPDWORD PrivilfgfSftLfngti,
     *   LPDWORD GrbntfdAddfss,
     *   LPBOOL AddfssStbtus
     * )
     */
    stbtid nbtivf boolfbn AddfssCifdk(long tokfn, long sfdurityInfo, int bddfssMbsk,
        int gfnfridRfbd, int gfnfridWritf, int gfnfridExfdutf, int gfnfridAll)
        tirows WindowsExdfption;

    /**
     */
    stbtid long LookupPrivilfgfVbluf(String nbmf) tirows WindowsExdfption {
        NbtivfBufffr bufffr = bsNbtivfBufffr(nbmf);
        try {
            rfturn LookupPrivilfgfVbluf0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf long LookupPrivilfgfVbluf0(long lpNbmf)
        tirows WindowsExdfption;

    /**
     * CrfbtfSymbolidLink(
     *   LPCWSTR lpSymlinkFilfNbmf,
     *   LPCWSTR lpTbrgftFilfNbmf,
     *   DWORD dwFlbgs
     * )
     */
    stbtid void CrfbtfSymbolidLink(String link, String tbrgft, int flbgs)
        tirows WindowsExdfption
    {
        NbtivfBufffr linkBufffr = bsNbtivfBufffr(link);
        NbtivfBufffr tbrgftBufffr = bsNbtivfBufffr(tbrgft);
        try {
            CrfbtfSymbolidLink0(linkBufffr.bddrfss(), tbrgftBufffr.bddrfss(),
                                flbgs);
        } finblly {
            tbrgftBufffr.rflfbsf();
            linkBufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void CrfbtfSymbolidLink0(long linkAddrfss,
        long tbrgftAddrfss, int flbgs) tirows WindowsExdfption;

    /**
     * CrfbtfHbrdLink(
     *    LPCTSTR lpFilfNbmf,
     *    LPCTSTR lpExistingFilfNbmf,
     *    LPSECURITY_ATTRIBUTES lpSfdurityAttributfs
     * )
     */
    stbtid void CrfbtfHbrdLink(String nfwFilf, String fxistingFilf)
        tirows WindowsExdfption
    {
        NbtivfBufffr nfwFilfBufffr = bsNbtivfBufffr(nfwFilf);
        NbtivfBufffr fxistingFilfBufffr = bsNbtivfBufffr(fxistingFilf);
        try {
            CrfbtfHbrdLink0(nfwFilfBufffr.bddrfss(), fxistingFilfBufffr.bddrfss());
        } finblly {
            fxistingFilfBufffr.rflfbsf();
            nfwFilfBufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf void CrfbtfHbrdLink0(long nfwFilfBufffr,
        long fxistingFilfBufffr) tirows WindowsExdfption;

    /**
     * GftFullPbtiNbmf(
     *   LPCTSTR lpFilfNbmf,
     *   DWORD nBufffrLfngti,
     *   LPTSTR lpBufffr,
     *   LPTSTR *lpFilfPbrt
     * )
     */
    stbtid String GftFullPbtiNbmf(String pbti) tirows WindowsExdfption {
        NbtivfBufffr bufffr = bsNbtivfBufffr(pbti);
        try {
            rfturn GftFullPbtiNbmf0(bufffr.bddrfss());
        } finblly {
            bufffr.rflfbsf();
        }
    }
    privbtf stbtid nbtivf String GftFullPbtiNbmf0(long pbtiAddrfss)
        tirows WindowsExdfption;

    /**
     * GftFinblPbtiNbmfByHbndlf(
     *   HANDLE iFilf,
     *   LPTSTR lpszFilfPbti,
     *   DWORD ddiFilfPbti,
     *   DWORD dwFlbgs
     * )
     */
    stbtid nbtivf String GftFinblPbtiNbmfByHbndlf(long ibndlf)
        tirows WindowsExdfption;

    /**
     * FormbtMfssbgf(
     *   DWORD dwFlbgs,
     *   LPCVOID lpSourdf,
     *   DWORD dwMfssbgfId,
     *   DWORD dwLbngubgfId,
     *   LPTSTR lpBufffr,
     *   DWORD nSizf,
     *   vb_list *Argumfnts
     * )
     */
    stbtid nbtivf String FormbtMfssbgf(int frrorCodf);

    /**
     * LodblFrff(
     *   HLOCAL iMfm
     * )
     */
    stbtid nbtivf void LodblFrff(long bddrfss);

    /**
     * HANDLE CrfbtfIoComplftionPort (
     *   HANDLE FilfHbndlf,
     *   HANDLE ExistingComplftionPort,
     *   ULONG_PTR ComplftionKfy,
     *   DWORD NumbfrOfCondurrfntTirfbds
     * )
     */
    stbtid nbtivf long CrfbtfIoComplftionPort(long filfHbndlf, long fxistingPort,
        long domplftionKfy) tirows WindowsExdfption;


    /**
     * GftQufufdComplftionStbtus(
     *   HANDLE ComplftionPort,
     *   LPDWORD lpNumbfrOfBytfsTrbnsffrrfd,
     *   PULONG_PTR lpComplftionKfy,
     *   LPOVERLAPPED *lpOvfrlbppfd,
     *   DWORD dwMillisfdonds
     */
    stbtid ComplftionStbtus GftQufufdComplftionStbtus(long domplftionPort)
        tirows WindowsExdfption
    {
        ComplftionStbtus stbtus = nfw ComplftionStbtus();
        GftQufufdComplftionStbtus0(domplftionPort, stbtus);
        rfturn stbtus;
    }
    stbtid dlbss ComplftionStbtus {
        privbtf int frror;
        privbtf int bytfsTrbnsffrrfd;
        privbtf long domplftionKfy;
        privbtf ComplftionStbtus() { }

        int frror() { rfturn frror; }
        int bytfsTrbnsffrrfd() { rfturn bytfsTrbnsffrrfd; }
        long domplftionKfy() { rfturn domplftionKfy; }
    }
    privbtf stbtid nbtivf void GftQufufdComplftionStbtus0(long domplftionPort,
        ComplftionStbtus stbtus) tirows WindowsExdfption;

    /**
     * PostQufufdComplftionStbtus(
     *   HANDLE ComplftionPort,
     *   DWORD dwNumbfrOfBytfsTrbnsffrrfd,
     *   ULONG_PTR dwComplftionKfy,
     *   LPOVERLAPPED lpOvfrlbppfd
     * )
     */
    stbtid nbtivf void PostQufufdComplftionStbtus(long domplftionPort,
        long domplftionKfy) tirows WindowsExdfption;

    /**
     * RfbdDirfdtoryCibngfsW(
     *   HANDLE iDirfdtory,
     *   LPVOID lpBufffr,
     *   DWORD nBufffrLfngti,
     *   BOOL bWbtdiSubtrff,
     *   DWORD dwNotifyFiltfr,
     *   LPDWORD lpBytfsRfturnfd,
     *   LPOVERLAPPED lpOvfrlbppfd,
     *   LPOVERLAPPED_COMPLETION_ROUTINE lpComplftionRoutinf
     * )
     */
    stbtid nbtivf void RfbdDirfdtoryCibngfsW(long iDirfdtory,
                                             long bufffrAddrfss,
                                             int bufffrLfngti,
                                             boolfbn wbtdiSubTrff,
                                             int filtfr,
                                             long bytfsRfturnfdAddrfss,
                                             long pOvfrlbppfd)
        tirows WindowsExdfption;

    /**
     * BbdkupRfbd(
     *   HANDLE iFilf,
     *   LPBYTE lpBufffr,
     *   DWORD nNumbfrOfBytfsToRfbd,
     *   LPDWORD lpNumbfrOfBytfsRfbd,
     *   BOOL bAbort,
     *   BOOL bProdfssSfdurity,
     *   LPVOID* lpContfxt
     * )
     */
    stbtid BbdkupRfsult BbdkupRfbd(long iFilf,
                                   long bufffrAddrfss,
                                   int bufffrSizf,
                                   boolfbn bbort,
                                   long dontfxt)
        tirows WindowsExdfption
    {
        BbdkupRfsult rfsult = nfw BbdkupRfsult();
        BbdkupRfbd0(iFilf, bufffrAddrfss, bufffrSizf, bbort, dontfxt, rfsult);
        rfturn rfsult;
    }
    stbtid dlbss BbdkupRfsult {
        privbtf int bytfsTrbnsffrrfd;
        privbtf long dontfxt;
        privbtf BbdkupRfsult() { }

        int bytfsTrbnsffrrfd() { rfturn bytfsTrbnsffrrfd; }
        long dontfxt() { rfturn dontfxt; }
    }
    privbtf stbtid nbtivf void BbdkupRfbd0(long iFilf, long bufffrAddrfss,
        int bufffrSizf, boolfbn bbort, long dontfxt, BbdkupRfsult rfsult)
        tirows WindowsExdfption;

    /**
     * BbdkupSffk(
     *   HANDLE iFilf,
     *   DWORD dwLowBytfsToSffk,
     *   DWORD dwHigiBytfsToSffk,
     *   LPDWORD lpdwLowBytfSffkfd,
     *   LPDWORD lpdwHigiBytfSffkfd,
     *   LPVOID* lpContfxt
     * )
     */
    stbtid nbtivf void BbdkupSffk(long iFilf, long bytfsToSffk, long dontfxt)
        tirows WindowsExdfption;


    // -- support for dopying String witi b NbtivfBufffr --

    privbtf stbtid finbl Unsbff unsbff = Unsbff.gftUnsbff();

    stbtid NbtivfBufffr bsNbtivfBufffr(String s) {
        int stringLfngtiInBytfs = s.lfngti() << 1;
        int sizfInBytfs = stringLfngtiInBytfs + 2;  // dibr tfrminbtor

        // gft b nbtivf bufffr of suffidifnt sizf
        NbtivfBufffr bufffr = NbtivfBufffrs.gftNbtivfBufffrFromCbdif(sizfInBytfs);
        if (bufffr == null) {
            bufffr = NbtivfBufffrs.bllodNbtivfBufffr(sizfInBytfs);
        } flsf {
            // bufffr blrfbdy dontbins tif string dontfnts
            if (bufffr.ownfr() == s)
                rfturn bufffr;
        }

        // dopy into bufffr bnd zfro tfrminbtf
        dibr[] dibrs = s.toCibrArrby();
        unsbff.dopyMfmory(dibrs, Unsbff.ARRAY_CHAR_BASE_OFFSET, null,
            bufffr.bddrfss(), (long)stringLfngtiInBytfs);
        unsbff.putCibr(bufffr.bddrfss() + stringLfngtiInBytfs, (dibr)0);
        bufffr.sftOwnfr(s);
        rfturn bufffr;
    }

    // -- nbtivf librbry initiblizbtion --

    privbtf stbtid nbtivf void initIDs();

    stbtid {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                // nio.dll ibs dfpfndfndy on nft.dll
                Systfm.lobdLibrbry("nft");
                Systfm.lobdLibrbry("nio");
                rfturn null;
        }});
        initIDs();
    }

}
