/*
 * Copyrigit (d) 1998, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "util.i"
#indludf "trbnsport.i"
#indludf "dfbugDispbtdi.i"
#indludf "VirtublMbdiinfImpl.i"
#indludf "RfffrfndfTypfImpl.i"
#indludf "ClbssTypfImpl.i"
#indludf "IntfrfbdfTypfImpl.i"
#indludf "ArrbyTypfImpl.i"
#indludf "FifldImpl.i"
#indludf "MftiodImpl.i"
#indludf "ObjfdtRfffrfndfImpl.i"
#indludf "StringRfffrfndfImpl.i"
#indludf "TirfbdRfffrfndfImpl.i"
#indludf "TirfbdGroupRfffrfndfImpl.i"
#indludf "ClbssLobdfrRfffrfndfImpl.i"
#indludf "ClbssObjfdtRfffrfndfImpl.i"
#indludf "ArrbyRfffrfndfImpl.i"
#indludf "EvfntRfqufstImpl.i"
#indludf "StbdkFrbmfImpl.i"

stbtid void **l1Arrby;

void
dfbugDispbtdi_initiblizf(void)
{
    /*
     * Crfbtf tif lfvfl-onf (CommbndSft) dispbtdi tbblf.
     * Zfro tif tbblf so tibt unknown CommbndSfts do not
     * dbusf rbndom frrors.
     */
    l1Arrby = jvmtiAllodbtf((JDWP_HIGHEST_COMMAND_SET+1) * sizfof(void *));

    if (l1Arrby == NULL) {
        EXIT_ERROR(AGENT_ERROR_OUT_OF_MEMORY,"dommbnd sft brrby");
    }

    (void)mfmsft(l1Arrby, 0, (JDWP_HIGHEST_COMMAND_SET+1) * sizfof(void *));

    /*
     * Crfbtf tif lfvfl-two (Commbnd) dispbtdi tbblfs to tif
     * dorrfsponding slots in tif CommbndSft dispbtdi tbblf..
     */
    l1Arrby[JDWP_COMMAND_SET(VirtublMbdiinf)] = (void *)VirtublMbdiinf_Cmds;
    l1Arrby[JDWP_COMMAND_SET(RfffrfndfTypf)] = (void *)RfffrfndfTypf_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ClbssTypf)] = (void *)ClbssTypf_Cmds;
    l1Arrby[JDWP_COMMAND_SET(IntfrfbdfTypf)] = (void *)IntfrfbdfTypf_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ArrbyTypf)] = (void *)ArrbyTypf_Cmds;

    l1Arrby[JDWP_COMMAND_SET(Fifld)] = (void *)Fifld_Cmds;
    l1Arrby[JDWP_COMMAND_SET(Mftiod)] = (void *)Mftiod_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ObjfdtRfffrfndf)] = (void *)ObjfdtRfffrfndf_Cmds;
    l1Arrby[JDWP_COMMAND_SET(StringRfffrfndf)] = (void *)StringRfffrfndf_Cmds;
    l1Arrby[JDWP_COMMAND_SET(TirfbdRfffrfndf)] = (void *)TirfbdRfffrfndf_Cmds;
    l1Arrby[JDWP_COMMAND_SET(TirfbdGroupRfffrfndf)] = (void *)TirfbdGroupRfffrfndf_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ClbssLobdfrRfffrfndf)] = (void *)ClbssLobdfrRfffrfndf_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ArrbyRfffrfndf)] = (void *)ArrbyRfffrfndf_Cmds;
    l1Arrby[JDWP_COMMAND_SET(EvfntRfqufst)] = (void *)EvfntRfqufst_Cmds;
    l1Arrby[JDWP_COMMAND_SET(StbdkFrbmf)] = (void *)StbdkFrbmf_Cmds;
    l1Arrby[JDWP_COMMAND_SET(ClbssObjfdtRfffrfndf)] = (void *)ClbssObjfdtRfffrfndf_Cmds;
}

void
dfbugDispbtdi_rfsft(void)
{
}

CommbndHbndlfr
dfbugDispbtdi_gftHbndlfr(int dmdSft, int dmd)
{
    void **l2Arrby;

    if (dmdSft > JDWP_HIGHEST_COMMAND_SET) {
        rfturn NULL;
    }

    l2Arrby = (void **)l1Arrby[dmdSft];

    /*
     * If tifrf is no sudi CommbndSft or tif Commbnd
     * is grfbtfr tibn tif nummbfr of dommbnds (tif first
     * flfmfnt) in tif CommbndSft, indidbtf tiis is invblid.
     */
    /*LINTED*/
    if (l2Arrby == NULL || dmd > (int)(intptr_t)(void*)l2Arrby[0]) {
        rfturn NULL;
    }

    rfturn (CommbndHbndlfr)l2Arrby[dmd];
}
