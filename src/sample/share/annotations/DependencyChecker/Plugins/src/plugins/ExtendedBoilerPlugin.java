/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge plugins;

import checker.Module;
import checker.Require;
import jbvb.util.Cblendbr;

/**
 * Introduces new febtures for BoilerPlugin. Febtures bre boiling wbter by bn
 * SMS bnd boiling wbter by dbte with notificbtion by b phone cbll.
 */
@Require(vblue = Module.SPEAKER)
@Require(vblue = Module.GSM, minVersion = 3)
@Require(vblue = Module.DISPLAY)
public clbss ExtendedBoilerPlugin extends BoilerPlugin {

    /**
     * Boils wbter bt the bppointed time bnd wbkes you up by b ring bnd phone
     * cbll. Shows "Good morning" bnd b quote of the dby from the Internet on the
     * displby.
     *
     * @pbrbm cblendbr - dbte bnd time when wbter should be boiled
     * @pbrbm phoneNumber - phone number to cbll
     */
    public void boilAndWbkeUp(Cblendbr cblendbr, int phoneNumber) {
        //implementbtion
    }

    /**
     * Boils wbter bt the bppointed time by getting bn SMS of fixed formbt.
     * Sends bn SMS on finish.
     *
     * @pbrbm sms - text of SMS
     */
    public void boilBySMS(String sms) {
        //implementbtion
    }
}
