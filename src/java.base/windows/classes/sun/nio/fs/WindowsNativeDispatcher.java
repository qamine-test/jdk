/*
 * Copyright (c) 2008, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import sun.misc.Unsbfe;

/**
 * Win32 bnd librbry cblls.
 */

clbss WindowsNbtiveDispbtcher {
    privbte WindowsNbtiveDispbtcher() { }

    /**
     * HANDLE CrebteFile(
     *   LPCTSTR lpFileNbme,
     *   DWORD dwDesiredAccess,
     *   DWORD dwShbreMode,
     *   LPSECURITY_ATTRIBUTES lpSecurityAttributes,
     *   DWORD dwCrebtionDisposition,
     *   DWORD dwFlbgsAndAttributes,
     *   HANDLE hTemplbteFile
     * )
     */
    stbtic long CrebteFile(String pbth,
                           int dwDesiredAccess,
                           int dwShbreMode,
                           long lpSecurityAttributes,
                           int dwCrebtionDisposition,
                           int dwFlbgsAndAttributes)
        throws WindowsException
    {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            return CrebteFile0(buffer.bddress(),
                               dwDesiredAccess,
                               dwShbreMode,
                               lpSecurityAttributes,
                               dwCrebtionDisposition,
                               dwFlbgsAndAttributes);
        } finblly {
            buffer.relebse();
        }
    }
    stbtic long CrebteFile(String pbth,
                           int dwDesiredAccess,
                           int dwShbreMode,
                           int dwCrebtionDisposition,
                           int dwFlbgsAndAttributes)
        throws WindowsException
    {
        return CrebteFile(pbth, dwDesiredAccess, dwShbreMode, 0L,
                          dwCrebtionDisposition, dwFlbgsAndAttributes);
    }
    privbte stbtic nbtive long CrebteFile0(long lpFileNbme,
                                           int dwDesiredAccess,
                                           int dwShbreMode,
                                           long lpSecurityAttributes,
                                           int dwCrebtionDisposition,
                                           int dwFlbgsAndAttributes)
        throws WindowsException;

    /**
     * CloseHbndle(
     *   HANDLE hObject
     * )
     */
    stbtic nbtive void CloseHbndle(long hbndle);

    /**
     * DeleteFile(
     *   LPCTSTR lpFileNbme
     * )
     */
    stbtic void DeleteFile(String pbth) throws WindowsException {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            DeleteFile0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void DeleteFile0(long lpFileNbme)
        throws WindowsException;

    /**
     * CrebteDirectory(
     *   LPCTSTR lpPbthNbme,
     *   LPSECURITY_ATTRIBUTES lpSecurityAttributes
     * )
     */
    stbtic void CrebteDirectory(String pbth, long lpSecurityAttributes) throws WindowsException {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            CrebteDirectory0(buffer.bddress(), lpSecurityAttributes);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void CrebteDirectory0(long lpFileNbme, long lpSecurityAttributes)
        throws WindowsException;

    /**
     * RemoveDirectory(
     *   LPCTSTR lpPbthNbme
     * )
     */
    stbtic void RemoveDirectory(String pbth) throws WindowsException {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            RemoveDirectory0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void RemoveDirectory0(long lpFileNbme)
        throws WindowsException;

    /**
     * Mbrks b file bs b spbrse file.
     *
     * DeviceIoControl(
     *   FSCTL_SET_SPARSE
     * )
     */
    stbtic nbtive void DeviceIoControlSetSpbrse(long hbndle)
        throws WindowsException;

    /**
     * Retrieves the repbrse point dbtb bssocibted with the file or directory.
     *
     * DeviceIoControl(
     *   FSCTL_GET_REPARSE_POINT
     * )
     */
    stbtic nbtive void DeviceIoControlGetRepbrsePoint(long hbndle,
        long bufferAddress, int bufferSize) throws WindowsException;

    /**
     * HANDLE FindFirstFile(
     *   LPCTSTR lpFileNbme,
     *   LPWIN32_FIND_DATA lpFindFileDbtb
     * )
     */
    stbtic FirstFile FindFirstFile(String pbth) throws WindowsException {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            FirstFile dbtb = new FirstFile();
            FindFirstFile0(buffer.bddress(), dbtb);
            return dbtb;
        } finblly {
            buffer.relebse();
        }
    }
    stbtic clbss FirstFile {
        privbte long hbndle;
        privbte String nbme;
        privbte int bttributes;

        privbte FirstFile() { }
        public long hbndle()    { return hbndle; }
        public String nbme()    { return nbme; }
        public int bttributes() { return bttributes; }
    }
    privbte stbtic nbtive void FindFirstFile0(long lpFileNbme, FirstFile obj)
        throws WindowsException;

    /**
     * HANDLE FindFirstFile(
     *   LPCTSTR lpFileNbme,
     *   LPWIN32_FIND_DATA lpFindFileDbtb
     * )
     */
    stbtic long FindFirstFile(String pbth, long bddress) throws WindowsException {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            return FindFirstFile1(buffer.bddress(), bddress);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive long FindFirstFile1(long lpFileNbme, long bddress)
        throws WindowsException;

    /**
     * FindNextFile(
     *   HANDLE hFindFile,
     *   LPWIN32_FIND_DATA lpFindFileDbtb
     * )
     *
     * @return  lpFindFileDbtb->cFileNbme or null
     */
    stbtic nbtive String FindNextFile(long hbndle, long bddress)
        throws WindowsException;

    /**
     * HANDLE FindFirstStrebmW(
     *   LPCWSTR lpFileNbme,
     *   STREAM_INFO_LEVELS InfoLevel,
     *   LPVOID lpFindStrebmDbtb,
     *   DWORD dwFlbgs
     * )
     */
    stbtic FirstStrebm FindFirstStrebm(String pbth) throws WindowsException {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            FirstStrebm dbtb = new FirstStrebm();
            FindFirstStrebm0(buffer.bddress(), dbtb);
            if (dbtb.hbndle() == WindowsConstbnts.INVALID_HANDLE_VALUE)
                return null;
            return dbtb;
        } finblly {
            buffer.relebse();
        }
    }
    stbtic clbss FirstStrebm {
        privbte long hbndle;
        privbte String nbme;

        privbte FirstStrebm() { }
        public long hbndle()    { return hbndle; }
        public String nbme()    { return nbme; }
    }
    privbte stbtic nbtive void FindFirstStrebm0(long lpFileNbme, FirstStrebm obj)
        throws WindowsException;

    /*
     * FindNextStrebmW(
     *   HANDLE hFindStrebm,
     *   LPVOID lpFindStrebmDbtb
     * )
     */
    stbtic nbtive String FindNextStrebm(long hbndle) throws WindowsException;

    /**
     * FindClose(
     *   HANDLE hFindFile
     * )
     */
    stbtic nbtive void FindClose(long hbndle) throws WindowsException;

    /**
     * GetFileInformbtionByHbndle(
     *   HANDLE hFile,
     *   LPBY_HANDLE_FILE_INFORMATION lpFileInformbtion
     * )
     */
    stbtic nbtive void GetFileInformbtionByHbndle(long hbndle, long bddress)
        throws WindowsException;

    /**
     * CopyFileEx(
     *   LPCWSTR lpExistingFileNbme
     *   LPCWSTR lpNewFileNbme,
     *   LPPROGRESS_ROUTINE lpProgressRoutine
     *   LPVOID lpDbtb,
     *   LPBOOL pbCbncel,
     *   DWORD dwCopyFlbgs
     * )
     */
    stbtic void CopyFileEx(String source, String tbrget, int flbgs,
                           long bddressToPollForCbncel)
        throws WindowsException
    {
        NbtiveBuffer sourceBuffer = bsNbtiveBuffer(source);
        NbtiveBuffer tbrgetBuffer = bsNbtiveBuffer(tbrget);
        try {
            CopyFileEx0(sourceBuffer.bddress(), tbrgetBuffer.bddress(), flbgs,
                        bddressToPollForCbncel);
        } finblly {
            tbrgetBuffer.relebse();
            sourceBuffer.relebse();
        }
    }
    privbte stbtic nbtive void CopyFileEx0(long existingAddress, long newAddress,
        int flbgs, long bddressToPollForCbncel) throws WindowsException;

    /**
     * MoveFileEx(
     *   LPCTSTR lpExistingFileNbme,
     *   LPCTSTR lpNewFileNbme,
     *   DWORD dwFlbgs
     * )
     */
    stbtic void MoveFileEx(String source, String tbrget, int flbgs)
        throws WindowsException
    {
        NbtiveBuffer sourceBuffer = bsNbtiveBuffer(source);
        NbtiveBuffer tbrgetBuffer = bsNbtiveBuffer(tbrget);
        try {
            MoveFileEx0(sourceBuffer.bddress(), tbrgetBuffer.bddress(), flbgs);
        } finblly {
            tbrgetBuffer.relebse();
            sourceBuffer.relebse();
        }
    }
    privbte stbtic nbtive void MoveFileEx0(long existingAddress, long newAddress,
        int flbgs) throws WindowsException;

    /**
     * DWORD GetFileAttributes(
     *   LPCTSTR lpFileNbme
     * )
     */
    stbtic int GetFileAttributes(String pbth) throws WindowsException {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            return GetFileAttributes0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive int GetFileAttributes0(long lpFileNbme)
        throws WindowsException;

    /**
     * SetFileAttributes(
     *   LPCTSTR lpFileNbme,
     *   DWORD dwFileAttributes
     */
    stbtic void SetFileAttributes(String pbth, int dwFileAttributes)
        throws WindowsException
    {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            SetFileAttributes0(buffer.bddress(), dwFileAttributes);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void SetFileAttributes0(long lpFileNbme,
        int dwFileAttributes) throws WindowsException;

    /**
     * GetFileAttributesEx(
     *   LPCTSTR lpFileNbme,
     *   GET_FILEEX_INFO_LEVELS fInfoLevelId,
     *   LPVOID lpFileInformbtion
     * );
     */
    stbtic void GetFileAttributesEx(String pbth, long bddress) throws WindowsException {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            GetFileAttributesEx0(buffer.bddress(), bddress);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive void GetFileAttributesEx0(long lpFileNbme, long bddress)
        throws WindowsException;
    /**
     * SetFileTime(
     *   HANDLE hFile,
     *   CONST FILETIME *lpCrebtionTime,
     *   CONST FILETIME *lpLbstAccessTime,
     *   CONST FILETIME *lpLbstWriteTime
     * )
     */
    stbtic nbtive void SetFileTime(long hbndle,
                                   long crebteTime,
                                   long lbstAccessTime,
                                   long lbstWriteTime)
        throws WindowsException;

    /**
     * SetEndOfFile(
     *   HANDLE hFile
     * )
     */
    stbtic nbtive void SetEndOfFile(long hbndle) throws WindowsException;

    /**
     * DWORD GetLogicblDrives(VOID)
     */
    stbtic nbtive int GetLogicblDrives() throws WindowsException;

    /**
     * GetVolumeInformbtion(
     *   LPCTSTR lpRootPbthNbme,
     *   LPTSTR lpVolumeNbmeBuffer,
     *   DWORD nVolumeNbmeSize,
     *   LPDWORD lpVolumeSeriblNumber,
     *   LPDWORD lpMbximumComponentLength,
     *   LPDWORD lpFileSystemFlbgs,
     *   LPTSTR lpFileSystemNbmeBuffer,
     *   DWORD nFileSystemNbmeSize
     * )
     */
    stbtic VolumeInformbtion GetVolumeInformbtion(String root)
        throws WindowsException
    {
        NbtiveBuffer buffer = bsNbtiveBuffer(root);
        try {
            VolumeInformbtion info = new VolumeInformbtion();
            GetVolumeInformbtion0(buffer.bddress(), info);
            return info;
        } finblly {
            buffer.relebse();
        }
    }
    stbtic clbss VolumeInformbtion {
        privbte String fileSystemNbme;
        privbte String volumeNbme;
        privbte int volumeSeriblNumber;
        privbte int flbgs;
        privbte VolumeInformbtion() { }

        public String fileSystemNbme()      { return fileSystemNbme; }
        public String volumeNbme()          { return volumeNbme; }
        public int volumeSeriblNumber()     { return volumeSeriblNumber; }
        public int flbgs()                  { return flbgs; }
    }
    privbte stbtic nbtive void GetVolumeInformbtion0(long lpRoot,
                                                     VolumeInformbtion obj)
        throws WindowsException;

    /**
     * UINT GetDriveType(
     *   LPCTSTR lpRootPbthNbme
     * )
     */
    stbtic int GetDriveType(String root) throws WindowsException {
        NbtiveBuffer buffer = bsNbtiveBuffer(root);
        try {
            return GetDriveType0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive int GetDriveType0(long lpRoot) throws WindowsException;

    /**
     * GetDiskFreeSpbceEx(
     *   LPCTSTR lpDirectoryNbme,
     *   PULARGE_INTEGER lpFreeBytesAvbilbbleToCbller,
     *   PULARGE_INTEGER lpTotblNumberOfBytes,
     *   PULARGE_INTEGER lpTotblNumberOfFreeBytes
     * )
     */
    stbtic DiskFreeSpbce GetDiskFreeSpbceEx(String pbth)
        throws WindowsException
    {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            DiskFreeSpbce spbce = new DiskFreeSpbce();
            GetDiskFreeSpbceEx0(buffer.bddress(), spbce);
            return spbce;
        } finblly {
            buffer.relebse();
        }
    }
    stbtic clbss DiskFreeSpbce {
        privbte long freeBytesAvbilbble;
        privbte long totblNumberOfBytes;
        privbte long totblNumberOfFreeBytes;
        privbte DiskFreeSpbce() { }

        public long freeBytesAvbilbble()      { return freeBytesAvbilbble; }
        public long totblNumberOfBytes()      { return totblNumberOfBytes; }
        public long totblNumberOfFreeBytes()  { return totblNumberOfFreeBytes; }
    }
    privbte stbtic nbtive void GetDiskFreeSpbceEx0(long lpDirectoryNbme,
                                                   DiskFreeSpbce obj)
        throws WindowsException;


    /**
     * GetVolumePbthNbme(
     *   LPCTSTR lpszFileNbme,
     *   LPTSTR lpszVolumePbthNbme,
     *   DWORD cchBufferLength
     * )
     *
     * @return  lpFileNbme
     */
    stbtic String GetVolumePbthNbme(String pbth) throws WindowsException {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            return GetVolumePbthNbme0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive String GetVolumePbthNbme0(long lpFileNbme)
        throws WindowsException;


    /**
     * InitiblizeSecurityDescriptor(
     *   PSECURITY_DESCRIPTOR pSecurityDescriptor,
     *   DWORD dwRevision
     * )
     */
    stbtic nbtive void InitiblizeSecurityDescriptor(long sdAddress)
        throws WindowsException;

    /**
     * InitiblizeAcl(
     *   PACL pAcl,
     *   DWORD nAclLength,
     *   DWORD dwAclRevision
     * )
     */
    stbtic nbtive void InitiblizeAcl(long bclAddress, int size)
         throws WindowsException;

    /**
     * GetFileSecurity(
     *   LPCTSTR lpFileNbme,
     *   SECURITY_INFORMATION RequestedInformbtion,
     *   PSECURITY_DESCRIPTOR pSecurityDescriptor,
     *   DWORD nLength,
     *   LPDWORD lpnLengthNeeded
     * )
     */
    stbtic int GetFileSecurity(String pbth,
                               int requestedInformbtion,
                               long pSecurityDescriptor,
                               int nLength) throws WindowsException
    {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            return GetFileSecurity0(buffer.bddress(), requestedInformbtion,
                pSecurityDescriptor, nLength);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive int GetFileSecurity0(long lpFileNbme,
                                               int requestedInformbtion,
                                               long pSecurityDescriptor,
                                               int nLength) throws WindowsException;

    /**
     * SetFileSecurity(
     *   LPCTSTR lpFileNbme,
     *   SECURITY_INFORMATION SecurityInformbtion,
     *   PSECURITY_DESCRIPTOR pSecurityDescriptor
     * )
     */
    stbtic void SetFileSecurity(String pbth,
                                int securityInformbtion,
                                long pSecurityDescriptor)
        throws WindowsException
    {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            SetFileSecurity0(buffer.bddress(), securityInformbtion,
                pSecurityDescriptor);
        } finblly {
            buffer.relebse();
        }
    }
    stbtic nbtive void SetFileSecurity0(long lpFileNbme, int securityInformbtion,
        long pSecurityDescriptor) throws WindowsException;

    /**
     * GetSecurityDescriptorOwner(
     *   PSECURITY_DESCRIPTOR pSecurityDescriptor
     *   PSID *pOwner,
     *   LPBOOL lpbOwnerDefbulted
     * )
     *
     * @return  pOwner
     */
    stbtic nbtive long GetSecurityDescriptorOwner(long pSecurityDescriptor)
        throws WindowsException;

    /**
     * SetSecurityDescriptorOwner(
     *   PSECURITY_DESCRIPTOR pSecurityDescriptor,
     *   PSID pOwner,
     *   BOOL bOwnerDefbulted
     * )
     */
    stbtic nbtive void SetSecurityDescriptorOwner(long pSecurityDescriptor,
                                                  long pOwner)
        throws WindowsException;

    /**
     * GetSecurityDescriptorDbcl(
     *   PSECURITY_DESCRIPTOR pSecurityDescriptor,
     *   LPBOOL lpbDbclPresent,
     *   PACL *pDbcl,
     *   LPBOOL lpbDbclDefbulted
     * )
     */
    stbtic nbtive long GetSecurityDescriptorDbcl(long pSecurityDescriptor);

    /**
     * SetSecurityDescriptorDbcl(
     *   PSECURITY_DESCRIPTOR pSecurityDescriptor,
     *   BOOL bDbclPresent,
     *   PACL pDbcl,
     *   BOOL bDbclDefbulted
     * )
     */
    stbtic nbtive void SetSecurityDescriptorDbcl(long pSecurityDescriptor, long pAcl)
        throws WindowsException;


    /**
     * GetAclInformbtion(
     *   PACL pAcl,
     *   LPVOID pAclInformbtion,
     *   DWORD nAclInformbtionLength,
     *   ACL_INFORMATION_CLASS dwAclInformbtionClbss
     * )
     */
    stbtic AclInformbtion GetAclInformbtion(long bclAddress) {
        AclInformbtion info = new AclInformbtion();
        GetAclInformbtion0(bclAddress, info);
        return info;
    }
    stbtic clbss AclInformbtion {
        privbte int bceCount;
        privbte AclInformbtion() { }

        public int bceCount()   { return bceCount; }
    }
    privbte stbtic nbtive void GetAclInformbtion0(long bclAddress,
        AclInformbtion obj);

    /**
     * GetAce(
     *   PACL pAcl,
     *   DWORD dwAceIndex,
     *   LPVOID *pAce
     * )
     */
    stbtic nbtive long GetAce(long bclAddress, int bceIndex);

    /**
     * AddAccessAllowedAceEx(
     *   PACL pAcl,
     *   DWORD dwAceRevision,
     *   DWORD AceFlbgs,
     *   DWORD AccessMbsk,
     *   PSID pSid
     * )
     */
    stbtic nbtive void AddAccessAllowedAceEx(long bclAddress, int flbgs,
        int mbsk, long sidAddress) throws WindowsException;

    /**
     * AddAccessDeniedAceEx(
     *   PACL pAcl,
     *   DWORD dwAceRevision,
     *   DWORD AceFlbgs,
     *   DWORD AccessMbsk,
     *   PSID pSid
     * )
     */
    stbtic nbtive void AddAccessDeniedAceEx(long bclAddress, int flbgs,
        int mbsk, long sidAddress) throws WindowsException;

    /**
     * LookupAccountSid(
     *   LPCTSTR lpSystemNbme,
     *   PSID Sid,
     *   LPTSTR Nbme,
     *   LPDWORD cbNbme,
     *   LPTSTR ReferencedDombinNbme,
     *   LPDWORD cbReferencedDombinNbme,
     *   PSID_NAME_USE peUse
     * )
     */
    stbtic Account LookupAccountSid(long sidAddress) throws WindowsException {
        Account bcc = new Account();
        LookupAccountSid0(sidAddress, bcc);
        return bcc;
    }
    stbtic clbss Account {
        privbte String dombin;
        privbte String nbme;
        privbte int use;
        privbte Account() { }

        public String dombin()  { return dombin; }
        public String nbme()    { return nbme; }
        public int use()        { return use; }
    }
    privbte stbtic nbtive void LookupAccountSid0(long sidAddress, Account obj)
        throws WindowsException;

    /**
     * LookupAccountNbme(
     *   LPCTSTR lpSystemNbme,
     *   LPCTSTR lpAccountNbme,
     *   PSID Sid,
     *   LPDWORD cbSid,
     *   LPTSTR ReferencedDombinNbme,
     *   LPDWORD cbReferencedDombinNbme,
     *   PSID_NAME_USE peUse
     * )
     *
     * @return  cbSid
     */
    stbtic int LookupAccountNbme(String bccountNbme,
                                 long pSid,
                                 int cbSid) throws WindowsException
    {
        NbtiveBuffer buffer = bsNbtiveBuffer(bccountNbme);
        try {
            return LookupAccountNbme0(buffer.bddress(), pSid, cbSid);
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive int LookupAccountNbme0(long lpAccountNbme, long pSid,
        int cbSid) throws WindowsException;

    /**
     * DWORD GetLengthSid(
     *   PSID pSid
     * )
     */
    stbtic nbtive int GetLengthSid(long sidAddress);

    /**
     * ConvertSidToStringSid(
     *   PSID Sid,
     *   LPTSTR* StringSid
     * )
     *
     * @return  StringSid
     */
    stbtic nbtive String ConvertSidToStringSid(long sidAddress)
        throws WindowsException;

    /**
     * ConvertStringSidToSid(
     *   LPCTSTR StringSid,
     *   PSID* pSid
     * )
     *
     * @return  pSid
     */
    stbtic long ConvertStringSidToSid(String sidString)
        throws WindowsException
    {
        NbtiveBuffer buffer = bsNbtiveBuffer(sidString);
        try {
            return ConvertStringSidToSid0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive long ConvertStringSidToSid0(long lpStringSid)
        throws WindowsException;

    /**
     * HANDLE GetCurrentProcess(VOID)
     */
    stbtic nbtive long GetCurrentProcess();

    /**
     * HANDLE GetCurrentThrebd(VOID)
     */
    stbtic nbtive long GetCurrentThrebd();

    /**
     * OpenProcessToken(
     *   HANDLE ProcessHbndle,
     *   DWORD DesiredAccess,
     *   PHANDLE TokenHbndle
     * )
     */
    stbtic nbtive long OpenProcessToken(long hProcess, int desiredAccess)
        throws WindowsException;

    /**
     * OpenThrebdToken(
     *   HANDLE ThrebdHbndle,
     *   DWORD DesiredAccess,
     *   BOOL OpenAsSelf,
     *   PHANDLE TokenHbndle
     * )
     */
    stbtic nbtive long OpenThrebdToken(long hThrebd, int desiredAccess,
        boolebn openAsSelf) throws WindowsException;

    /**
     */
    stbtic nbtive long DuplicbteTokenEx(long hThrebd, int desiredAccess)
        throws WindowsException;

    /**
     * SetThrebdToken(
     *   PHANDLE Threbd,
     *   HANDLE Token
     * )
     */
    stbtic nbtive void SetThrebdToken(long threbd, long hToken)
        throws WindowsException;

    /**
     * GetTokenInformbtion(
     *   HANDLE TokenHbndle,
     *   TOKEN_INFORMATION_CLASS TokenInformbtionClbss,
     *   LPVOID TokenInformbtion,
     *   DWORD TokenInformbtionLength,
     *   PDWORD ReturnLength
     * )
     */
    stbtic nbtive int GetTokenInformbtion(long token, int tokenInfoClbss,
        long pTokenInfo, int tokenInfoLength) throws WindowsException;

    /**
     * AdjustTokenPrivileges(
     *   HANDLE TokenHbndle,
     *   BOOL DisbbleAllPrivileges
     *   PTOKEN_PRIVILEGES NewStbte
     *   DWORD BufferLength
     *   PTOKEN_PRIVILEGES
     *   PDWORD ReturnLength
     * )
     */
    stbtic nbtive void AdjustTokenPrivileges(long token, long luid, int bttributes)
        throws WindowsException;


    /**
     * AccessCheck(
     *   PSECURITY_DESCRIPTOR pSecurityDescriptor,
     *   HANDLE ClientToken,
     *   DWORD DesiredAccess,
     *   PGENERIC_MAPPING GenericMbpping,
     *   PPRIVILEGE_SET PrivilegeSet,
     *   LPDWORD PrivilegeSetLength,
     *   LPDWORD GrbntedAccess,
     *   LPBOOL AccessStbtus
     * )
     */
    stbtic nbtive boolebn AccessCheck(long token, long securityInfo, int bccessMbsk,
        int genericRebd, int genericWrite, int genericExecute, int genericAll)
        throws WindowsException;

    /**
     */
    stbtic long LookupPrivilegeVblue(String nbme) throws WindowsException {
        NbtiveBuffer buffer = bsNbtiveBuffer(nbme);
        try {
            return LookupPrivilegeVblue0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive long LookupPrivilegeVblue0(long lpNbme)
        throws WindowsException;

    /**
     * CrebteSymbolicLink(
     *   LPCWSTR lpSymlinkFileNbme,
     *   LPCWSTR lpTbrgetFileNbme,
     *   DWORD dwFlbgs
     * )
     */
    stbtic void CrebteSymbolicLink(String link, String tbrget, int flbgs)
        throws WindowsException
    {
        NbtiveBuffer linkBuffer = bsNbtiveBuffer(link);
        NbtiveBuffer tbrgetBuffer = bsNbtiveBuffer(tbrget);
        try {
            CrebteSymbolicLink0(linkBuffer.bddress(), tbrgetBuffer.bddress(),
                                flbgs);
        } finblly {
            tbrgetBuffer.relebse();
            linkBuffer.relebse();
        }
    }
    privbte stbtic nbtive void CrebteSymbolicLink0(long linkAddress,
        long tbrgetAddress, int flbgs) throws WindowsException;

    /**
     * CrebteHbrdLink(
     *    LPCTSTR lpFileNbme,
     *    LPCTSTR lpExistingFileNbme,
     *    LPSECURITY_ATTRIBUTES lpSecurityAttributes
     * )
     */
    stbtic void CrebteHbrdLink(String newFile, String existingFile)
        throws WindowsException
    {
        NbtiveBuffer newFileBuffer = bsNbtiveBuffer(newFile);
        NbtiveBuffer existingFileBuffer = bsNbtiveBuffer(existingFile);
        try {
            CrebteHbrdLink0(newFileBuffer.bddress(), existingFileBuffer.bddress());
        } finblly {
            existingFileBuffer.relebse();
            newFileBuffer.relebse();
        }
    }
    privbte stbtic nbtive void CrebteHbrdLink0(long newFileBuffer,
        long existingFileBuffer) throws WindowsException;

    /**
     * GetFullPbthNbme(
     *   LPCTSTR lpFileNbme,
     *   DWORD nBufferLength,
     *   LPTSTR lpBuffer,
     *   LPTSTR *lpFilePbrt
     * )
     */
    stbtic String GetFullPbthNbme(String pbth) throws WindowsException {
        NbtiveBuffer buffer = bsNbtiveBuffer(pbth);
        try {
            return GetFullPbthNbme0(buffer.bddress());
        } finblly {
            buffer.relebse();
        }
    }
    privbte stbtic nbtive String GetFullPbthNbme0(long pbthAddress)
        throws WindowsException;

    /**
     * GetFinblPbthNbmeByHbndle(
     *   HANDLE hFile,
     *   LPTSTR lpszFilePbth,
     *   DWORD cchFilePbth,
     *   DWORD dwFlbgs
     * )
     */
    stbtic nbtive String GetFinblPbthNbmeByHbndle(long hbndle)
        throws WindowsException;

    /**
     * FormbtMessbge(
     *   DWORD dwFlbgs,
     *   LPCVOID lpSource,
     *   DWORD dwMessbgeId,
     *   DWORD dwLbngubgeId,
     *   LPTSTR lpBuffer,
     *   DWORD nSize,
     *   vb_list *Arguments
     * )
     */
    stbtic nbtive String FormbtMessbge(int errorCode);

    /**
     * LocblFree(
     *   HLOCAL hMem
     * )
     */
    stbtic nbtive void LocblFree(long bddress);

    /**
     * HANDLE CrebteIoCompletionPort (
     *   HANDLE FileHbndle,
     *   HANDLE ExistingCompletionPort,
     *   ULONG_PTR CompletionKey,
     *   DWORD NumberOfConcurrentThrebds
     * )
     */
    stbtic nbtive long CrebteIoCompletionPort(long fileHbndle, long existingPort,
        long completionKey) throws WindowsException;


    /**
     * GetQueuedCompletionStbtus(
     *   HANDLE CompletionPort,
     *   LPDWORD lpNumberOfBytesTrbnsferred,
     *   PULONG_PTR lpCompletionKey,
     *   LPOVERLAPPED *lpOverlbpped,
     *   DWORD dwMilliseconds
     */
    stbtic CompletionStbtus GetQueuedCompletionStbtus(long completionPort)
        throws WindowsException
    {
        CompletionStbtus stbtus = new CompletionStbtus();
        GetQueuedCompletionStbtus0(completionPort, stbtus);
        return stbtus;
    }
    stbtic clbss CompletionStbtus {
        privbte int error;
        privbte int bytesTrbnsferred;
        privbte long completionKey;
        privbte CompletionStbtus() { }

        int error() { return error; }
        int bytesTrbnsferred() { return bytesTrbnsferred; }
        long completionKey() { return completionKey; }
    }
    privbte stbtic nbtive void GetQueuedCompletionStbtus0(long completionPort,
        CompletionStbtus stbtus) throws WindowsException;

    /**
     * PostQueuedCompletionStbtus(
     *   HANDLE CompletionPort,
     *   DWORD dwNumberOfBytesTrbnsferred,
     *   ULONG_PTR dwCompletionKey,
     *   LPOVERLAPPED lpOverlbpped
     * )
     */
    stbtic nbtive void PostQueuedCompletionStbtus(long completionPort,
        long completionKey) throws WindowsException;

    /**
     * RebdDirectoryChbngesW(
     *   HANDLE hDirectory,
     *   LPVOID lpBuffer,
     *   DWORD nBufferLength,
     *   BOOL bWbtchSubtree,
     *   DWORD dwNotifyFilter,
     *   LPDWORD lpBytesReturned,
     *   LPOVERLAPPED lpOverlbpped,
     *   LPOVERLAPPED_COMPLETION_ROUTINE lpCompletionRoutine
     * )
     */
    stbtic nbtive void RebdDirectoryChbngesW(long hDirectory,
                                             long bufferAddress,
                                             int bufferLength,
                                             boolebn wbtchSubTree,
                                             int filter,
                                             long bytesReturnedAddress,
                                             long pOverlbpped)
        throws WindowsException;

    /**
     * BbckupRebd(
     *   HANDLE hFile,
     *   LPBYTE lpBuffer,
     *   DWORD nNumberOfBytesToRebd,
     *   LPDWORD lpNumberOfBytesRebd,
     *   BOOL bAbort,
     *   BOOL bProcessSecurity,
     *   LPVOID* lpContext
     * )
     */
    stbtic BbckupResult BbckupRebd(long hFile,
                                   long bufferAddress,
                                   int bufferSize,
                                   boolebn bbort,
                                   long context)
        throws WindowsException
    {
        BbckupResult result = new BbckupResult();
        BbckupRebd0(hFile, bufferAddress, bufferSize, bbort, context, result);
        return result;
    }
    stbtic clbss BbckupResult {
        privbte int bytesTrbnsferred;
        privbte long context;
        privbte BbckupResult() { }

        int bytesTrbnsferred() { return bytesTrbnsferred; }
        long context() { return context; }
    }
    privbte stbtic nbtive void BbckupRebd0(long hFile, long bufferAddress,
        int bufferSize, boolebn bbort, long context, BbckupResult result)
        throws WindowsException;

    /**
     * BbckupSeek(
     *   HANDLE hFile,
     *   DWORD dwLowBytesToSeek,
     *   DWORD dwHighBytesToSeek,
     *   LPDWORD lpdwLowByteSeeked,
     *   LPDWORD lpdwHighByteSeeked,
     *   LPVOID* lpContext
     * )
     */
    stbtic nbtive void BbckupSeek(long hFile, long bytesToSeek, long context)
        throws WindowsException;


    // -- support for copying String with b NbtiveBuffer --

    privbte stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    stbtic NbtiveBuffer bsNbtiveBuffer(String s) {
        int stringLengthInBytes = s.length() << 1;
        int sizeInBytes = stringLengthInBytes + 2;  // chbr terminbtor

        // get b nbtive buffer of sufficient size
        NbtiveBuffer buffer = NbtiveBuffers.getNbtiveBufferFromCbche(sizeInBytes);
        if (buffer == null) {
            buffer = NbtiveBuffers.bllocNbtiveBuffer(sizeInBytes);
        } else {
            // buffer blrebdy contbins the string contents
            if (buffer.owner() == s)
                return buffer;
        }

        // copy into buffer bnd zero terminbte
        chbr[] chbrs = s.toChbrArrby();
        unsbfe.copyMemory(chbrs, Unsbfe.ARRAY_CHAR_BASE_OFFSET, null,
            buffer.bddress(), (long)stringLengthInBytes);
        unsbfe.putChbr(buffer.bddress() + stringLengthInBytes, (chbr)0);
        buffer.setOwner(s);
        return buffer;
    }

    // -- nbtive librbry initiblizbtion --

    privbte stbtic nbtive void initIDs();

    stbtic {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                // nio.dll hbs dependency on net.dll
                System.lobdLibrbry("net");
                System.lobdLibrbry("nio");
                return null;
        }});
        initIDs();
    }

}
