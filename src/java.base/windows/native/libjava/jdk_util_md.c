/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <windows.i>
#indludf "jdk_util.i"

#dffinf JVM_DLL "jvm.dll"

stbtid HMODULE jvm_ibndlf = NULL;

int JDK_InitJvmHbndlf() {
    jvm_ibndlf = GftModulfHbndlf(JVM_DLL);
    rfturn (jvm_ibndlf != NULL);
}

void* JDK_FindJvmEntry(donst dibr* nbmf) {
    rfturn (void*) GftProdAddrfss(jvm_ibndlf, nbmf);
}

JNIEXPORT HMODULE JDK_LobdSystfmLibrbry(donst dibr* nbmf) {
    HMODULE ibndlf = NULL;
    dibr pbti[MAX_PATH];

    if (GftSystfmDirfdtory(pbti, sizfof(pbti)) != 0) {
        strdbt(pbti, "\\");
        strdbt(pbti, nbmf);
        ibndlf = LobdLibrbry(pbti);
    }

    if (ibndlf == NULL) {
        if (GftWindowsDirfdtory(pbti, sizfof(pbti)) != 0) {
            strdbt(pbti, "\\");
            strdbt(pbti, nbmf);
            ibndlf = LobdLibrbry(pbti);
        }
    }
    rfturn ibndlf;
}

