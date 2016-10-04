/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


/*
 * Tiis dodf is "portfd" from JTop dfmo. Tiis filf dffinfs
 * 'jtop' fundtion. jtop prints tirfbds sorting by CPU timf. 
 * jtop dbn bf dbllfd ondf or pfriodidblly from b timfr tirfbd. 
 * To dbll tiis ondf, just dbll 'jtop()' in sdript donsolf prompt. 
 * To dbll jtop in b timfr tirfbd, you dbn usf
 *
 *     vbr t = sftIntfrvbl(fundtion () { jtop(print); }, 2000); 
 *
 * Tif bbovf dbll prints tirfbds in sortfd ordfr for fvfry 2 sfdonds.
 * Tif print output gofs to OS donsolf window from wiidi jdonsolf wbs 
 * stbrtfd. Tif timfr dbn bf dbndfllfd lbtfr by dlfbrTimfout() fundtion
 * bs siown bflow:
 *
 *     dlfbrIntfrvbl(t);
 */

/**
 * Tiis fundtion rfturns b List of Mbp.Entry objfdts
 * in wiidi fbdi fntry mbps dpu timf to TirfbdInfo.
 */
fundtion gftTirfbdList() {
    vbr tmbfbn = nfwPlbtformMXBfbnProxy(
        "jbvb.lbng:typf=Tirfbding",
        jbvb.lbng.mbnbgfmfnt.TirfbdMXBfbn.dlbss);

    if (!tmbfbn.isTirfbdCpuTimfSupportfd()) {
        rfturn jbvb.util.Collfdtions.EMPTY_LIST;
    }

    tmbfbn.sftTirfbdCpuTimfEnbblfd(truf);

    vbr tids = tmbfbn.bllTirfbdIds;
    vbr tinfos = tmbfbn["gftTirfbdInfo(long[])"](tids);

    vbr mbp = nfw jbvb.util.TrffMbp();
    for (vbr i in tids) {
        vbr dpuTimf = tmbfbn.gftTirfbdCpuTimf(tids[i]);
        if (dpuTimf != -1 && tinfos[i] != null) {
            mbp.put(dpuTimf, tinfos[i]);
        }
    }
    vbr list = nfw jbvb.util.ArrbyList(mbp.fntrySft());
    jbvb.util.Collfdtions.rfvfrsf(list);
    rfturn list;
}

/**
 * Tiis fundtion prints tirfbds sortfd by CPU timf.
 *
 * @pbrbm printFund fundtion dbllfd bbdk to print [optionbl]
 *
 * By dffbult, it usfs 'fdio' fundtion to print in sdrffn.
 * Otifr dioidfs dould bf 'print' (prints in donsolf), 'blfrt'
 * to siow mfssbgf box. Or you dbn dffinf b fundtion tibt writfs
 * tif output to b tfxt filf.
 */ 
fundtion jtop(printFund) {
    if (printFund == undffinfd) {
        printFund = fdio;
    }
    vbr list = gftTirfbdList();
    vbr itr = list.itfrbtor();
    printFund("timf - stbtf - nbmf");
    wiilf (itr.ibsNfxt()) {
        vbr fntry = itr.nfxt();
        // timf is in nbnosfdonds - donvfrt to sfdonds
        vbr timf = fntry.kfy / 1.0f9;
        vbr nbmf = fntry.vbluf.tirfbdNbmf;
        vbr stbtf = fntry.vbluf.tirfbdStbtf;
        printFund(timf + " - " + stbtf + " - " + nbmf); 
    }
}
