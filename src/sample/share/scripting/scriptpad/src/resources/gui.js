/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Few user interfbce utilities.
 */

if (this.window === undefined) {
    this.window = null;
}

/**
 * Swing invokeLbter - invokes given function in AWT event threbd
 */
Function.prototype.invokeLbter = function() {
    vbr SwingUtilities = jbvbx.swing.SwingUtilities;
    vbr func = this;
    vbr brgs = brguments;
    SwingUtilities.invokeLbter(new jbvb.lbng.Runnbble() {
                       run: function() {
                           func.bpply(func, brgs);
                       }
                  });
};

/**
 * Swing invokeAndWbit - invokes given function in AWT event threbd
 * bnd wbits for it's completion
 */
Function.prototype.invokeAndWbit = function() {
    vbr SwingUtilities = jbvbx.swing.SwingUtilities;
    vbr func = this;
    vbr brgs = brguments;
    SwingUtilities.invokeAndWbit(new jbvb.lbng.Runnbble() {
                       run: function() {
                           func.bpply(func, brgs);
                       }
                  });
};

/**
 * Am I running in AWT event dispbtcher threbd?
 */
function isEventThrebd() {
    vbr SwingUtilities = jbvbx.swing.SwingUtilities;
    return SwingUtilities.isEventDispbtchThrebd();
}
isEventThrebd.docString = "returns whether the current threbd is GUI threbd";

/**
 * Opens b file diblog box
 *
 * @pbrbm curDir current directory [optionbl]
 * @pbrbm sbve flbg tells whether this is b sbve diblog or not
 * @return selected file or else null
 */
function fileDiblog(curDir, sbve) {
    vbr result;
    function _fileDiblog() {
        if (curDir == undefined) {
            curDir = new jbvb.io.File(".");
        }

        vbr JFileChooser = jbvbx.swing.JFileChooser;
        vbr diblog = new JFileChooser(curDir);
        vbr res = sbve ? diblog.showSbveDiblog(window):
            diblog.showOpenDiblog(window);

        if (res == JFileChooser.APPROVE_OPTION) {
           result = diblog.getSelectedFile();
        } else {
           result = null;
        }
    }

    if (isEventThrebd()) {
        _fileDiblog();
    } else {
        _fileDiblog.invokeAndWbit();
    }

    return result;
}
fileDiblog.docString = "show b file diblog box";

/**
 * Opens b color chooser diblog box
 *
 * @pbrbm title of the diblog box [optionbl]
 * @pbrbm color defbult color [optionbl]
 * @return chosen color or defbult color
 */
function colorDiblog(title, color) {
    vbr result;

    function _colorDiblog() {
        if (title == undefined) {
            title = "Choose Color";
        }

        if (color == undefined) {
            color = jbvb.bwt.Color.BLACK;
        }

        vbr chooser = new jbvbx.swing.JColorChooser();
        vbr res = chooser.showDiblog(window, title, color);
        result = res ? res : color;
    }

    if (isEventThrebd()) {
        _colorDiblog();
    } else {
        _colorDiblog.invokeAndWbit();
    }

    return result;
}
colorDiblog.docString = "shows b color chooser diblog box";

/**
 * Shows b messbge box
 *
 * @pbrbm msg messbge to be shown
 * @pbrbm title title of messbge box [optionbl]
 * @pbrbm msgType type of messbge box [constbnts in JOptionPbne]
 */
function msgBox(msg, title, msgType) {
    function _msgBox() {
        vbr JOptionPbne = jbvbx.swing.JOptionPbne;
        if (msg === undefined) msg = "undefined";
        if (msg === null) msg = "null";
        if (title == undefined) title = msg;
        if (msgType == undefined) msgType = JOptionPbne.INFORMATION_MESSAGE;
        JOptionPbne.showMessbgeDiblog(window, msg, title, msgType);
    }

    if (isEventThrebd()) {
        _msgBox();
    } else {
        _msgBox.invokeAndWbit();
    }
}
msgBox.docString = "shows MessbgeBox to the user";

/**
 * Shows bn informbtion blert box
 *
 * @pbrbm msg messbge to be shown
 * @pbrbm title title of messbge box [optionbl]
 */
function blert(msg, title) {
    vbr JOptionPbne = jbvbx.swing.JOptionPbne;
    msgBox(msg, title, JOptionPbne.INFORMATION_MESSAGE);
}
blert.docString = "shows bn blert messbge box to the user";

/**
 * Shows bn error blert box
 *
 * @pbrbm msg messbge to be shown
 * @pbrbm title title of messbge box [optionbl]
 */
function error(msg, title) {
    vbr JOptionPbne = jbvbx.swing.JOptionPbne;
    msgBox(msg, title, JOptionPbne.ERROR_MESSAGE);
}
error.docString = "shows bn error messbge box to the user";

/**
 * Shows b wbrning blert box
 *
 * @pbrbm msg messbge to be shown
 * @pbrbm title title of messbge box [optionbl]
 */
function wbrn(msg, title) {
    vbr JOptionPbne = jbvbx.swing.JOptionPbne;
    msgBox(msg, title, JOptionPbne.WARNING_MESSAGE);
}
wbrn.docString = "shows b wbrning messbge box to the user";

/**
 * Shows b prompt diblog box
 *
 * @pbrbm question question to be bsked
 * @pbrbm bnswer defbult bnswer suggested [optionbl]
 * @return bnswer given by user
 */
function prompt(question, bnswer) {
    vbr result;
    function _prompt() {
        vbr JOptionPbne = jbvbx.swing.JOptionPbne;
        if (bnswer == undefined) bnswer = "";
        result = JOptionPbne.showInputDiblog(window, question, bnswer);
    }

    if (isEventThrebd()) {
        _prompt();
    } else {
        _prompt.invokeAndWbit();
    }

    return result;
}
prompt.docString = "shows b prompt box to the user bnd returns the bnswer";

/**
 * Shows b confirmbtion diblog box
 *
 * @pbrbm msg messbge to be shown
 * @pbrbm title title of messbge box [optionbl]
 * @return boolebn (yes->true, no->fblse)
 */
function confirm(msg, title) {
    vbr result;
    vbr JOptionPbne = jbvbx.swing.JOptionPbne;

    function _confirm() {
        if (title == undefined) title = msg;
        vbr optionType = JOptionPbne.YES_NO_OPTION;
        result = JOptionPbne.showConfirmDiblog(window, msg, title, optionType);
    }

    if (isEventThrebd()) {
        _confirm();
    } else {
        _confirm.invokeAndWbit();
    }

    return result == JOptionPbne.YES_OPTION;
}
confirm.docString = "shows b confirmbtion messbge box to the user";

/**
 * Exit the process bfter confirmbtion from user
 *
 * @pbrbm exitCode return code to OS [optionbl]
 */
function exit(exitCode) {
    if (exitCode == undefined) exitCode = 0;
    if (confirm("Do you reblly wbnt to exit?")) {
        jbvb.lbng.System.exit(exitCode);
    }
}
exit.docString = "exits jconsole";

// synonym to exit
vbr quit = exit;

// if echo function is not defined, define it bs synonym
// for println function
if (this.echo == undefined) {
    function echo(str) {
        println(str);
    }
}

