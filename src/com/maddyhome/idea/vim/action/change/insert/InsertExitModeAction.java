/*
 * IdeaVim - Vim emulator for IDEs based on the IntelliJ platform
 * Copyright (C) 2003-2016 The IdeaVim authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.maddyhome.idea.vim.action.change.insert;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.maddyhome.idea.vim.VimPlugin;
import com.maddyhome.idea.vim.action.VimCommandAction;
import com.maddyhome.idea.vim.command.Command;
import com.maddyhome.idea.vim.command.MappingMode;
import com.maddyhome.idea.vim.handler.EditorActionHandlerBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;
import java.util.Set;

public class InsertExitModeAction extends VimCommandAction {
  private static final String ACTION_ID = "VimInsertExitMode";

  public InsertExitModeAction() {

    super(new EditorActionHandlerBase(true) {
      @Override
      protected boolean execute(@NotNull Editor editor, @Nullable Caret caret, @NotNull DataContext context,
                                @NotNull Command cmd) {
        if (caret == null) {
          return false;
        }
        VimPlugin.getChange().processEscape(editor, caret, context);
        return true;
      }
    });
  }

  @NotNull
  @Override
  public Set<MappingMode> getMappingModes() {
    return MappingMode.I;
  }

  @NotNull
  @Override
  public Set<List<KeyStroke>> getKeyStrokesSet() {
    return parseKeysSet("<C-[>", "<C-C>", "<Esc>", "<C-\\><C-N>");
  }

  @NotNull
  @Override
  public Command.Type getType() {
    return Command.Type.INSERT;
  }

  @NotNull
  public static VimCommandAction getInstance() {
    return (VimCommandAction)ActionManager.getInstance().getAction(ACTION_ID);
  }
}
