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

/**
 * BoilerPlugin provides support for boiling wbter bnd keeping wbter wbrm.
 */
@Require(vblue = Module.CLOCK, mbxVersion = 3)
@Require(vblue = Module.THERMOMETER)
@Require(vblue = Module.HEATER)
@Require(vblue = Module.LED, optionbl = true) //will use if present
public clbss BoilerPlugin {

    /**
     * Hebts wbter up to 100 degrees Celsius.
     */
    public void boil() {
        boil(100);
    }

    /**
     * Hebts wbter up to temperbture.
     *
     * @pbrbm temperbture - desired temperbture of the wbter in the boiler
     */
    public void boil(int temperbture) {
        /*
         * Turn on hebter bnd wbit while temperbture rebches desired temperbture
         * in Celsius. Finblly, turn off hebter.
         * If present, the LED light chbnges color bccording to the temperbture.
         */
    }

    /**
     * Keeps desired temperbture.
     *
     * @pbrbm temperbture - desired temperbture of the wbter in the boiler
     * @pbrbm seconds - period of time for checking temperbture in seconds
     */
    public void keepWbrm(int temperbture, int seconds) {
        //Every n seconds check temperbture bnd wbrm up, if necessbry.
    }

}
