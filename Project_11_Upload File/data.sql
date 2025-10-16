USE [master];
GO

-- Xóa DB cũ (nếu tồn tại)
IF EXISTS (SELECT name FROM sys.databases WHERE name = N'PRJ301_PROJECT2')
BEGIN
    ALTER DATABASE [PRJ301_PROJECT2] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE [PRJ301_PROJECT2];
END
GO

-- Tạo mới database
CREATE DATABASE [PRJ301_PROJECT2];
GO

-- Sử dụng database
USE [PRJ301_PROJECT2];
GO

-- Tạo bảng người dùng có thêm cột avatarBase64
CREATE TABLE [dbo].[tblUsers](
    [userID] NVARCHAR(50) NOT NULL,
    [fullName] NVARCHAR(100) NULL,
    [password] NVARCHAR(50) NULL,
    [roleID] NVARCHAR(50) NULL,
    [status] BIT NULL,
    [avatarBase64] NVARCHAR(MAX) NULL,   -- Cột chứa ảnh dưới dạng chuỗi Base64
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
    [userID] ASC
)WITH (
    PAD_INDEX  = OFF, 
    STATISTICS_NORECOMPUTE  = OFF, 
    IGNORE_DUP_KEY = OFF, 
    ALLOW_ROW_LOCKS  = ON, 
    ALLOW_PAGE_LOCKS  = ON
) ON [PRIMARY]
) ON [PRIMARY];
GO

-- Thêm dữ liệu mẫu (không có avatar)
INSERT INTO [dbo].[tblUsers] ([userID], [fullName], [password], [roleID], [status], [avatarBase64]) VALUES 
(N'admin',   N'Administrator',     N'1',  N'AD', 1, NULL),
(N'hungnq',  N'Nguyen Quang Hung', N'1',  N'AD', 1, NULL),
(N'se190011',N'Tran Manh Hung',    N'hcm',N'MB', 0, NULL),
(N'he181122',N'Hoang Minh Thoa',  N'hn', N'MB', 1, NULL),
(N'de175533',N'Vo Thi Hoang Oanh',N'dn', N'MB', 1, NULL),
(N'qe175533',N'Pham Tuan Minh',   N'qn', N'MB', 1, NULL),
(N'ce175533',N'Mai Thanh Quan',   N'ct', N'MB', 1, NULL);
GO
