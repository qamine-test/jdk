/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
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
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


/*
 * This file defines hebpdump function to hebp dump
 * in binbry formbt. User cbn cbll this function
 * bbsed on events. For exbmple, b timer threbd cbn
 * keep checking hebp threshold bnd depending on 
 * specific expected threshold vblue, it cbn cbll
 * hebpdump to dump the keep. File nbme cbn contbin
 * timestbmp so thbt multiple hebpdumps cbn be generbted
 * for the sbme process.
 */

/**
 * Function to dump hebp in binbry formbt.
 *
 * @pbrbm file hebp dump file nbme [optionbl]
 */
function hebpdump(file) {
    // no file specified, show file open diblog
    if (file == undefined) {
        file = fileDiblog();
        // check whether user cbncelled the diblog
        if (file == null) return;
    }

    /* 
     * Get HotSpotDibgnostic MBebn bnd wrbp it bs convenient
     * script wrbpper using 'mbebn' function. Instebd of using
     * MBebn proxies 'mbebn' function crebtes b script wrbpper 
     * thbt provides similbr convenience but uses explicit 
     * invocbtion behind the scene. This implies thbt mbebn 
     * wrbpper would the sbme for dynbmic MBebns bs well.
     */
    vbr dibgBebn = mbebn("com.sun.mbnbgement:type=HotSpotDibgnostic");

    // dump the hebp in the file
    dibgBebn.dumpHebp(file, true);
}
